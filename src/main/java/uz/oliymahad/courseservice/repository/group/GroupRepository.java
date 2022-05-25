package uz.oliymahad.courseservice.repository.group;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.oliymahad.courseservice.entity.group.GroupEntity;

public interface GroupRepository extends JpaRepository<GroupEntity, Long> {
}
