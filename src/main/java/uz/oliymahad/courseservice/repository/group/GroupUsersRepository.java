package uz.oliymahad.courseservice.repository.group;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.oliymahad.courseservice.entity.group.GroupUsersEntity;

public interface GroupUsersRepository extends JpaRepository<GroupUsersEntity, Long> {
}
