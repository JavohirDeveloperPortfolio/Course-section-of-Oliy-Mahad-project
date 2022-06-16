package uz.oliymahad.courseservice.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.oliymahad.courseservice.dto.*;
import uz.oliymahad.courseservice.dto.response.ApiResponse;
import uz.oliymahad.courseservice.dto.response.Response;
import uz.oliymahad.courseservice.entity.course.CourseEntity;
import uz.oliymahad.courseservice.entity.quequeue.QueueEntity;
import uz.oliymahad.courseservice.entity.quequeue.Status;
import uz.oliymahad.courseservice.feign.UserFeign;
import uz.oliymahad.courseservice.repository.CourseRepository;
import uz.oliymahad.courseservice.repository.QueueRepository;
import uz.oliymahad.dto.request.UsersIDSRequest;
import uz.oliymahad.dto.response.UserDataResponse;
import uz.oliymahad.model.entity.UserEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static uz.oliymahad.courseservice.dto.response.Response.*;

@Service
@RequiredArgsConstructor
public class QueueService implements BaseService<QueueDto, Long, QueueEntity, Pageable>, Response {

    private final QueueRepository queueRepository;
    private final CourseRepository courseRepository;
    private final ModelMapper modelMapper;

    private final UserFeign userFeign;


    @Override
    public ApiResponse<Void> add(QueueDto queueDto) {
//        boolean exist = userFeign.isExist(queueDto.getUserId());
//        if (!exist) {
//            return new ApiResponse<>(USER + NOT_FOUND,false);
//        }
        Optional<CourseEntity> optionalCourse = courseRepository.findById(queueDto.getCourseId());
        if (optionalCourse.isEmpty()) {
            return new ApiResponse<>(COURSE + NOT_FOUND, false);
        }
        QueueEntity queueEntity = modelMapper.map(queueDto, QueueEntity.class);
        queueEntity.setCourse(optionalCourse.get());
        queueEntity.setStatus(Status.PENDING);
        queueRepository.save(queueEntity);
        return new ApiResponse<>(SUCCESSFULLY_SAVED, true);
    }

    @Override
    public ApiResponse<Page<QueueEntity>> getList(Pageable page) {
        return new ApiResponse<>(DATA_LIST, true, queueRepository.findAll(page));

    }

    @Override
    public ApiResponse<QueueDto> get(Long id) {
        Optional<QueueEntity> optionalQueue = queueRepository.findById(id);
        if (optionalQueue.isEmpty()) {
            return new ApiResponse<>(QUEUE + NOT_FOUND, false);
        }
        QueueDto queueDto = modelMapper.map(optionalQueue.get(), QueueDto.class);
        return new ApiResponse<>(QUEUE, true, queueDto);
    }

    @Override
    public ApiResponse<Void> delete(Long id) {
        Optional<QueueEntity> optionalQueue = queueRepository.findById(id);
        if (optionalQueue.isEmpty()) {
            return new ApiResponse<>(QUEUE + NOT_FOUND, false);
        }
        queueRepository.delete(optionalQueue.get());
        return new ApiResponse<>(SUCCESSFULLY_DELETED, true);
    }

    @Override
    public ApiResponse<Void> edit(Long id, QueueDto queueDto) {
        Optional<QueueEntity> optionalQueue = queueRepository.findById(id);
        if (optionalQueue.isEmpty()) {
            return new ApiResponse<>(QUEUE + NOT_FOUND, false);
        }
        QueueEntity queueEntity = optionalQueue.get();
        if (queueDto.getAppliedDate() == null)
            queueDto.setAppliedDate(queueEntity.getAppliedDate());
        modelMapper.map(queueDto, queueEntity);
        queueRepository.save(queueEntity);
        return new ApiResponse<>(SUCCESSFULLY_UPDATED, true);
    }

    public ApiResponse<List<Long>> getUserCourseQueue(Long userId, Long courseId) {
        List<Long> userCourseQueue = queueRepository.getUserCourseQueue(userId, courseId);
        return new ApiResponse<>(SUCCESS, true, userCourseQueue);
    }

    public ApiResponse<List<Long>> getUsersByFilter(FilterQueueForGroupsDTO filterQueueDTO) {
        List<Long> users = queueRepository.filterByCourseStatusGenderLimitForGroups(filterQueueDTO.getCourseId(), filterQueueDTO.getStatus(), filterQueueDTO.getGender(), filterQueueDTO.getLimit());
        return new ApiResponse<>(SUCCESS, true, users);
    }


    public ApiResponse<List<QueueEntity>> getQueueByFilter(Long userId, String gender, String status, Long courseId, String appliedDate) {
        String appliedDateAfter = null;
        if (appliedDate != null) {
            appliedDateAfter = getDayAfterDay(appliedDate);
        }
        List<QueueEntity> queueByFilter = queueRepository.getQueueByFilter(userId, gender, status, courseId);
        return new ApiResponse<>(SUCCESS, true, queueByFilter);

    }


    private String getDayAfterDay(String day) {
        String sDay = day.substring(0, 10);
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(sDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long l = date.getTime() + 86400000;
        Date date1 = new Date(l);
        String afterDay = new SimpleDateFormat("yyyy-MM-dd").format(date1);
        return afterDay;
    }

    public List<UserDataResponse> getUserDetailsInQueue(UsersIDSRequest usersIDSRequest) {
        return userFeign.getUsers(usersIDSRequest);
    }


    public ApiResponse<?> getQueueDetails(PageRequest pageRequest) {

        Page<QueueEntity> page = queueRepository.findAll(pageRequest);
        List<Long> userIds = new ArrayList<>();
        for (QueueEntity q : page.getContent()) {
            userIds.add(q.getUserId());
        }

        List<UserDataResponse> users = userFeign.getUsers(new UsersIDSRequest(userIds));
        List<QueueUserDetailsDTO> queueUserDetailsDTOS = creatingQueueUserDetailsResponse(page.getContent(), users);
        QueueUserPageableResponse dataResponses = modelMapper.map(page, QueueUserPageableResponse.class);
        dataResponses.setContent(queueUserDetailsDTOS);
        return new ApiResponse<>(SUCCESS, true, dataResponses);
    }

    private List<QueueUserDetailsDTO> creatingQueueUserDetailsResponse(List<QueueEntity> queues, List<UserDataResponse> users) {
        List<QueueUserDetailsDTO> queueUserDetailsDTOS = new ArrayList<>();

        queues.forEach(queue -> {
            queueUserDetailsDTOS.add(modelMapper.map(queue, QueueUserDetailsDTO.class));
        });
        int index = 0;
        for (QueueEntity queue : queues) {
            UserDataResponse userDataResponse =
                    users.parallelStream().filter(u -> u.getId() == queue.getUserId()).findFirst().orElse(new UserDataResponse());
            queueUserDetailsDTOS.get(index++).setUserData(userDataResponse);
        }

        return queueUserDetailsDTOS;
    }
}
