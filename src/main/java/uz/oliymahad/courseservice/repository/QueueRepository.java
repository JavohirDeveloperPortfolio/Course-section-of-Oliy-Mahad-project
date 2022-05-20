package uz.oliymahad.courseservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.oliymahad.courseservice.entity.quequeue.QueueEntity;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface QueueRepository extends JpaRepository<QueueEntity,Long> {
    List<QueueEntity> findAllByUserId(long id);

    @Query("select q from QueueEntity q where q.courseEntity.id = ?1")
    List<QueueEntity> findAllByCourseEntityId(long id, PageRequest pageable);

}
