package uz.oliymahad.courseservice.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.oliymahad.courseservice.dto.response.ApiResponse;
import uz.oliymahad.courseservice.dto.CourseDto;
import uz.oliymahad.courseservice.dto.response.CourseSectionDto;
import uz.oliymahad.courseservice.dto.response.Response;
import uz.oliymahad.courseservice.entity.course.CourseEntity;
import uz.oliymahad.courseservice.feign.UserFeign;
import uz.oliymahad.courseservice.repository.CourseRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class CourseService implements BaseService<CourseDto,Long,CourseEntity, Pageable> , Response {
    private final UserFeign userFeign;
    private final CourseRepository courseRepository;
    private final ModelMapper modelMapper;

    @Override
    public ApiResponse<Void> add(CourseDto courseDto) {
        boolean exists = courseRepository.existsByName(courseDto.getName());
        if (exists) {
            return new ApiResponse<>(COURSE + ALREADY_EXIST,false);
        }
        CourseEntity courseEntity = modelMapper.map(courseDto, CourseEntity.class);
        courseRepository.save(courseEntity);
        return new ApiResponse<>(SUCCESSFULLY_SAVED,true);
    }

    @Override
    public ApiResponse<Page<CourseEntity>> getList(Pageable pageable) {
        return new ApiResponse<>(DATA_LIST,true,courseRepository.findAll(pageable));
    }

    public Page<CourseSectionDto> getLists(Pageable pageable) {
        Page<CourseEntity> courseEntities = courseRepository.findAll(pageable);
        List<CourseSectionDto> list = courseEntities.getContent().size() > 0 ?
                courseEntities.getContent().stream().map(u -> modelMapper.map(u, CourseSectionDto.class)).toList() :
                new ArrayList<>();
        return new PageImpl<>(list, courseEntities.getPageable(), courseEntities.getTotalPages());
    }

    @Override
    public ApiResponse<CourseDto> get(Long id) {
        Optional<CourseEntity> optionalCourse = courseRepository.findById(id);
        if (optionalCourse.isEmpty()) {
            return new ApiResponse<>(COURSE+NOT_FOUND,false);
        }
        CourseDto courseDto = modelMapper.map(optionalCourse.get(), CourseDto.class);
        return new ApiResponse<>(COURSE,true,courseDto);
    }

    @Override
    public ApiResponse<Void> delete(Long id) {
        Optional<CourseEntity> optionalCourse = courseRepository.findById(id);
        if (optionalCourse.isEmpty()) {
            return new ApiResponse<>(COURSE + NOT_FOUND,false);
        }
        CourseEntity courseEntity = optionalCourse.get();
        courseRepository.delete(courseEntity);
        return new ApiResponse<>(SUCCESSFULLY_DELETED,true);
    }

    @Override
    public ApiResponse<Void> edit(Long id, CourseDto courseDto) {
        Optional<CourseEntity> optionalCourse = courseRepository.findById(id);
        if (optionalCourse.isEmpty()) {
            return new ApiResponse<>(COURSE + NOT_FOUND,false);
        }
        CourseEntity courseEntity = optionalCourse.get();
        modelMapper.map(courseDto,courseEntity);
        courseRepository.save(courseEntity);
        return new ApiResponse<>(SUCCESSFULLY_UPDATED,true);
    }

    public ApiResponse<CourseDto> getByName (String name) {
        Optional<CourseEntity> optionalCourseEntity = courseRepository.findByName(name);
        if (optionalCourseEntity.isEmpty()) {
            return new ApiResponse<>(COURSE + NOT_FOUND,false);
        }
        CourseDto courseDto = modelMapper.map(optionalCourseEntity.get(), CourseDto.class);
        return new ApiResponse<>(COURSE,true,courseDto);
    }
}