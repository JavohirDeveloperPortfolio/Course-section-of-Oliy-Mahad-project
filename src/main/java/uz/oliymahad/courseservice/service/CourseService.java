package uz.oliymahad.courseservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.oliymahad.courseservice.dto.ApiResponse;
import uz.oliymahad.courseservice.dto.CourseDto;
import uz.oliymahad.courseservice.entity.course.CourseEntity;
import uz.oliymahad.courseservice.feign.UserFeign;
import uz.oliymahad.courseservice.repository.CourseRepository;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CourseService {
    private final UserFeign userFeign;
    private final CourseRepository courseRepository;

    public ApiResponse addCourse (CourseDto courseDto) {
        boolean exist = userFeign.isExist(courseDto.getAdminId());
        if (!exist) {
            return new ApiResponse("User not found",false);
        }
        boolean existsByName = courseRepository.existsByName(courseDto.getName());
        if (existsByName) {
            return new ApiResponse("Course already exist",false);
        }
        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setName(courseDto.getName());
        courseEntity.setDescription(courseDto.getDescription());
        courseEntity.setPrice(courseDto.getPrice());
        courseEntity.setAdminId(courseDto.getAdminId());
        courseEntity.setDuration(courseDto.getDuration());
        courseRepository.save(courseEntity);
        return new ApiResponse("Successfully saved",true);
    }

    public ApiResponse getCourses () {
        List<CourseEntity> courseEntities = courseRepository.findAll();
        return new ApiResponse("Course List",true,courseEntities);
    }
    
    public ApiResponse getCourseById (Long id) {
        Optional<CourseEntity> optionalCourse = courseRepository.findById(id);
        if (optionalCourse.isEmpty()) {
            return new ApiResponse("Course not found",false);
        }
        CourseEntity courseEntity = optionalCourse.get();
        return new ApiResponse("Course",true,courseEntity);
    }

    public ApiResponse updateCourse(Long id, CourseDto courseDto) {
        Optional<CourseEntity> optionalCourse = courseRepository.findById(id);
        if (optionalCourse.isEmpty()) {
            return new ApiResponse("Course not found",false);
        }
        CourseEntity courseEntity = optionalCourse.get();
        courseEntity.setDuration(courseDto.getDuration());
        courseEntity.setPrice(courseDto.getPrice());
        courseEntity.setDescription(courseDto.getDescription());
        courseEntity.setName(courseDto.getName());
        courseRepository.save(courseEntity);
        return new ApiResponse("Successfully updated",true);
    }

    public ApiResponse deleteCourse(Long id) {
        Optional<CourseEntity> optionalCourse = courseRepository.findById(id);
        if (optionalCourse.isEmpty()) {
            return new ApiResponse("Course not found",false);
        }
        CourseEntity courseEntity = optionalCourse.get();
        courseRepository.delete(courseEntity);
        return new ApiResponse("Successfully deleted",true);
    }

}
