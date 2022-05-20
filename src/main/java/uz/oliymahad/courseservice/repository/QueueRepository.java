package uz.oliymahad.courseservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.oliymahad.courseservice.entity.quequeue.QueueEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface QueueRepository extends JpaRepository<QueueEntity,Long> {
    List<QueueEntity> findAllByUserId(long id);
}
