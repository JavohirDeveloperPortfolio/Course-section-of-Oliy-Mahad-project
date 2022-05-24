package uz.oliymahad.courseservice.repository;

import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.oliymahad.courseservice.entity.quequeue.QueueEntity;

import java.util.List;

@Repository
public interface QueueRepository extends JpaRepository<QueueEntity,Long> {
    List<QueueEntity> findAllByUserId(long id);

    @Query(value = "select rank from (select user_id,applied_date, rank() over (order by applied_date) from queue_entity where course_entity_id = :courseId) as sub where user_id = :userId",nativeQuery = true)
     List<Long>  getUserCourseQueue(@Param("userId") Long userId, @Param("courseId") Long courseId);

    @Query(value = "select user_id from (select user_id,RANK() over(order by applied_date asc) from queue_entity where course_entity_id = :courseId and status = :status and gender =:gender ) as sub limit :limit",nativeQuery = true)
    List<Long> filterByCourseStatusGenderLimit(@Param("courseId") Long courseId,@Param("status") String status,@Param("gender") String gender,@Param("limit") Long limit);

    @Query(value = "select user_id from (select user_id,RANK() over(order by applied_date asc) from queue_entity where course_entity_id = :courseId  ) as sub limit :limit",nativeQuery = true)
    List<Long> filterByCourseLimit(@Param("courseId") Long courseId,@Param("limit") Long limit);

    @Query(value ="select user_id from (select user_id,RANK() over(order by applied_date asc) from queue_entity where course_entity_id = :courseId  and gender =:gender ) as sub limit :limit",nativeQuery = true )
    List<Long> filterByCourseGenderLimit(@Param("courseId") Long courseId,@Param("gender") String gender,@Param("limit") Long limit);

    @Query(value = "select user_id from (select user_id,RANK() over(order by applied_date asc) from queue_entity where course_entity_id = :courseId and status = :status  ) as sub limit :limit",nativeQuery = true)
    List<Long> filterByCourseStatusLimit(@Param("courseId")Long courseId,@Param("status") String status,@Param("limit") Long limit);
}
