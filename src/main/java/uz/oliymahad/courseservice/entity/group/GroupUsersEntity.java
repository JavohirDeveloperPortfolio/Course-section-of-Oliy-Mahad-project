package uz.oliymahad.courseservice.entity.group;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.oliymahad.courseservice.entity.BaseEntity;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "group_users_entity")
public class GroupUsersEntity extends BaseEntity {

    @Column(name = "user_id")
    private Long userId ;

    @ManyToOne
    private GroupEntity group ;

}
