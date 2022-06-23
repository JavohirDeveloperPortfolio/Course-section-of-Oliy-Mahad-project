package uz.oliymahad.courseservice.repository;

import feign.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.oliymahad.courseservice.entity.quequeue.QueueEntity;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface QueueRepository extends JpaRepository<QueueEntity,Long> {
    List<QueueEntity> findAllByUserId(long id);

    @Query(value = "select rank from (select user_id,applieddate, rank() over (order by applieddate) from queue_entity where course_id = :courseId) as sub where user_id = :userId", nativeQuery = true)
    List<Long> getUserCourseQueue(@Param("userId") Long userId, @Param("courseId") Long courseId);

    @Query(value = "select * from (select *,RANK() over(order by applieddate asc) from queue_entity where course_id = :courseId and status = :status and gender = :gender ) as sub  limit :limit", nativeQuery = true)
    List<Long> filterByCourseStatusGenderLimitForGroups(@Param("courseId") Long courseId, @Param("status") String status, @Param("gender") String gender, @Param("limit") Long limit);


    @Query(value = "select *from filter_all_parameters1(i_user_id := :userId, i_gender := :gender, i_status := :status, i_course_id := :courseId)",nativeQuery = true)
    List<QueueEntity> getQueueByFilter(
            @Param("userId") Long userId,
            @Param("gender") String gender,
            @Param("status") String status,
            @Param("courseId") Long courseId

    );

    @Query("select q from QueueEntity q ")
    List<QueueEntity> findAllByCourseEntityId( PageRequest pageable);

}
