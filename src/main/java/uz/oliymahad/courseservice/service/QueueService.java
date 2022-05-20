package uz.oliymahad.courseservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uz.oliymahad.courseservice.dto.ApiResponse;
import uz.oliymahad.courseservice.dto.QueueDto;
import uz.oliymahad.courseservice.entity.course.CourseEntity;
import uz.oliymahad.courseservice.entity.quequeue.OrderEnum;
import uz.oliymahad.courseservice.entity.quequeue.QueueEntity;
import uz.oliymahad.courseservice.repository.CourseRepository;
import uz.oliymahad.courseservice.repository.QueueRepository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QueueService implements BaseService<QueueDto> {

    private final QueueRepository queueRepository;
    private final CourseRepository courseRepository;



    @Override
    public ApiResponse add(QueueDto queueDto) {

        Optional<CourseEntity> byId = courseRepository.findById(queueDto.getCourseId());
        if (byId.isEmpty())
            return new ApiResponse("course Not found",false);


        QueueEntity queueEntity=new QueueEntity();
        queueEntity.setUserId(queueDto.getUserId());
        queueEntity.setAppliedDate(queueDto.getAppliedDate());
        queueEntity.setOrderEnum(OrderEnum.PENDING);
        queueEntity.setCourseEntity(byId.get());
        QueueEntity save = queueRepository.save(queueEntity);
        return new ApiResponse("Success",true,save);
    }

    @Override
    public ApiResponse getList(int age, int pageSize, long courseId) {
        PageRequest pageable = PageRequest.of(age, pageSize, Sort.by("appliedDate").descending());
        List<QueueEntity> all = queueRepository.findAllByCourseEntityId(courseId,pageable);
        return new ApiResponse("Success",true,all);
    }



    @Override
    public ApiResponse get(long id) {
        Optional<QueueEntity> byId = queueRepository.findById(id);
        if (byId.isEmpty())
            return new ApiResponse("Queue is not found",false);

        return new ApiResponse("Success",true,byId.get());
    }

    @Override
    public ApiResponse delete(long id) {
        Optional<QueueEntity> byId = queueRepository.findById(id);
        if (byId.isEmpty())
            return new ApiResponse("Queue is not found",false);
        queueRepository.delete(byId.get());

        return new ApiResponse("Success",true);

    }

    @Override
    public ApiResponse edit(long id, QueueDto queueDto) {
        return null;
    }




    public ApiResponse getUserQueues(Long userId) {
        List<QueueEntity> allByUserId = queueRepository.findAllByUserId(userId);
        return new ApiResponse("Success",true,allByUserId);
    }
}
