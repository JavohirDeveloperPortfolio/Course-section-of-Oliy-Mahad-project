package uz.oliymahad.courseservice.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.oliymahad.courseservice.dto.*;
import uz.oliymahad.courseservice.entity.course.CourseEntity;
import uz.oliymahad.courseservice.entity.quequeue.QueueEntity;
import uz.oliymahad.courseservice.entity.quequeue.Status;
import uz.oliymahad.courseservice.feign.UserFeign;
import uz.oliymahad.courseservice.repository.CourseRepository;
import uz.oliymahad.courseservice.repository.QueueRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QueueService implements BaseService<QueueDto,Long,QueueEntity,Pageable> , Response {

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
    public ApiResponse<Page<QueueEntity>> getList(Pageable page) {
        return new ApiResponse<>(DATA_LIST,true, queueRepository.findAll(page));

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
        if(queueDto.getAppliedDate() == null)
            queueDto.setAppliedDate(queueEntity.getAppliedDate());
        modelMapper.map(queueDto,queueEntity);
        queueRepository.save(queueEntity);
        return new ApiResponse<>(SUCCESSFULLY_UPDATED,true);
    }

    public ApiResponse<List<Long>> getUserCourseQueue(Long userId,Long courseId){
        List<Long> userCourseQueue = queueRepository.getUserCourseQueue(userId, courseId);
        return new ApiResponse<>(SUCCESS,true,userCourseQueue);
    }

    public ApiResponse<List<Long>> getUsersByFilter(FilterQueueForGroupsDTO filterQueueDTO){
        List<Long> users = queueRepository.filterByCourseStatusGenderLimitForGroups(filterQueueDTO.getCourseId(), filterQueueDTO.getStatus(), filterQueueDTO.getGender(),filterQueueDTO.getLimit());
        return new ApiResponse<>(SUCCESS,true,users);
    }


    public ApiResponse<Page<QueueEntity>> getQueueByFilter(Long userId,String gender,String status,Long courseId,String appliedDate,Pageable pageable){
        if(userId == null) {
            userId = Long.valueOf(-1);
        }
        if(gender == null)
            gender = "null";
        if(status == null)
            status = "null";
        if(courseId == null)
            courseId = Long.valueOf(-1);
        if(appliedDate == null)
            appliedDate = "2015-01-01";

        Page<QueueEntity> queueByFilter = queueRepository.getQueueByFilter(userId, gender, status, courseId,appliedDate,pageable);
        return new ApiResponse<>(SUCCESS,true,queueByFilter);

    }


    public List<QueueResponseDto> getQueueListByFilter(Long userId,String gender,String status,Long courseId,String appliedDate,Pageable pageable){
        if(userId == null) {
            userId = Long.valueOf(-1);
        }
        if(gender == null)
            gender = "null";
        if(status == null)
            status = "null";
        if(courseId == null)
            courseId = Long.valueOf(-1);
        if(appliedDate == null)
            appliedDate = "2015-01-01";

        List<QueueResponseDto> queueResponseDtos = new ArrayList<>();

        for (QueueEntity queueEntity : queueRepository.getQueueByFilter(userId, gender, status, courseId, appliedDate, pageable)) {
           QueueResponseDto queueResponseDto = new QueueResponseDto();
           queueResponseDto.setGender(queueEntity.getGender().name());
           if(queueEntity.getCourse() != null)
               queueResponseDto.setCourseId(queueEntity.getCourse().getId());
           queueResponseDto.setUserId(queueEntity.getUserId());
           queueResponseDto.setStatus(queueEntity.getStatus().name());
           queueResponseDto.setAppliedDate(queueEntity.getAppliedDate());
           queueResponseDtos.add(queueResponseDto);
        }
        return queueResponseDtos;
    }





}
