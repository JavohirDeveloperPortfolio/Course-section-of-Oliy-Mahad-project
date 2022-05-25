package uz.oliymahad.courseservice.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.oliymahad.courseservice.dto.ApiResponse;
import uz.oliymahad.courseservice.dto.QueueDto;
import uz.oliymahad.courseservice.dto.Response;
import uz.oliymahad.courseservice.entity.course.CourseEntity;
import uz.oliymahad.courseservice.entity.quequeue.QueueEntity;
import uz.oliymahad.courseservice.entity.quequeue.Status;
import uz.oliymahad.courseservice.feign.UserFeign;
import uz.oliymahad.courseservice.repository.CourseRepository;
import uz.oliymahad.courseservice.repository.QueueRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QueueService implements BaseService<QueueDto,Long,QueueEntity, Pageable> , Response {

    private final QueueRepository queueRepository;
    private final CourseRepository courseRepository;
    private final UserFeign userFeign;
    private final ModelMapper modelMapper;


    @Override
    public ApiResponse<Void> add(QueueDto queueDto) {
//        boolean exist = userFeign.isExist(queueDto.getUserId());
//        if (!exist) {
//            return new ApiResponse<>(USER + NOT_FOUND,false);
//        }
        Optional<CourseEntity> optionalCourse = courseRepository.findById(queueDto.getCourseId());
        if (optionalCourse.isEmpty()) {
            return new ApiResponse<>(COURSE + NOT_FOUND,false);
        }
        QueueEntity queueEntity = modelMapper.map(queueDto, QueueEntity.class);
        queueEntity.setCourse(optionalCourse.get());
        queueEntity.setStatus(Status.PENDING);
        queueRepository.save(queueEntity);
        return new ApiResponse<>(SUCCESSFULLY_SAVED,true);
    }

    @Override
    public ApiResponse<Page<QueueEntity>> getList(Pageable pageable) {
        return new ApiResponse<>(DATA_LIST,true,queueRepository.findAll(pageable));
    }


    @Override
    public ApiResponse<QueueDto> get(Long id) {
        Optional<QueueEntity> optionalQueue = queueRepository.findById(id);
        if (optionalQueue.isEmpty()) {
            return new ApiResponse<>(QUEUE + NOT_FOUND,false);
        }
        QueueDto queueDto = modelMapper.map(optionalQueue.get(), QueueDto.class);
        return new ApiResponse<>(QUEUE,true,queueDto);
    }

    @Override
    public ApiResponse<Void> delete(Long id) {
        Optional<QueueEntity> optionalQueue = queueRepository.findById(id);
        if (optionalQueue.isEmpty()) {
            return new ApiResponse<>(QUEUE + NOT_FOUND,false);
        }
        queueRepository.delete(optionalQueue.get());
        return new ApiResponse<>(SUCCESSFULLY_DELETED,true);
    }

    @Override
    public ApiResponse<Void> edit(Long id, QueueDto queueDto) {
        Optional<QueueEntity> optionalQueue = queueRepository.findById(id);
        if (optionalQueue.isEmpty()) {
            return new ApiResponse<>(QUEUE + NOT_FOUND,false);
        }
        QueueEntity queueEntity = optionalQueue.get();
        modelMapper.map(queueDto,queueEntity);
        return new ApiResponse<>(SUCCESSFULLY_UPDATED,true);
    }
}
