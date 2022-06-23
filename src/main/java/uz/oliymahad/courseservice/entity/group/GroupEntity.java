package uz.oliymahad.courseservice.entity.group;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import uz.oliymahad.courseservice.entity.BaseEntity;
import uz.oliymahad.courseservice.entity.course.CourseEntity;
import uz.oliymahad.courseservice.entity.quequeue.EGender;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "group_entity")
    public class GroupEntity extends BaseEntity {

    private String name;

    private Long membersCount ;

    @Enumerated
    private EGender type ;

    @Enumerated
    private GroupStatusEnum groupStatus ;

    private Date startDate;

    @ManyToOne
    private CourseEntity course ;

    @OneToMany(mappedBy = "group")
    private List<GroupUsersEntity> groupUsers;

}
