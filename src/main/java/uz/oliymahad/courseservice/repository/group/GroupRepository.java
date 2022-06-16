package uz.oliymahad.courseservice.repository.group;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.oliymahad.courseservice.entity.group.GroupEntity;

import java.util.Date;
import java.util.List;

public interface GroupRepository extends JpaRepository<GroupEntity, Long> {
//    List<GroupEntity> findGroupEntitiesByCreatedDateBetween(Date createdDate, Date createdDate2) ;
//    List<GroupEntity> findGroupEntitiesByCreatedDate_Month(int createdDate_month);
}
