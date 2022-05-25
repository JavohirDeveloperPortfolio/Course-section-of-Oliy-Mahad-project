package uz.oliymahad.courseservice.entity.group;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.oliymahad.courseservice.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "group_users_entity")
public class GroupUsersEntity extends BaseEntity {

    private Long userId ;

    @ManyToOne
    @Column(name = "group_id")
    private GroupEntity groupId ;

}
