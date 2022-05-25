package uz.oliymahad.courseservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.oliymahad.courseservice.entity.course.CourseEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity,Long> {
    boolean existsByName(String name);
    Optional<CourseEntity> findByName(String name);
    boolean existsById(String id);
}
