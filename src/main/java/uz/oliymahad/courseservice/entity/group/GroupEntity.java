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
@Entity(name = "group_entity")
public class GroupEntity extends BaseEntity {

    private String name;

    private Long membersCount ;

    @Enumerated
    private EGender type ;

    @CreatedDate
    @Column(name = "created_date")
    private Date createdDate ;

    @ManyToOne
    @Column(name = "course_id")
    private CourseEntity courseId ;

    @OneToMany(mappedBy = "groupId")
    @Column(name = "group_users")
    private List<GroupUsersEntity> groupUsers;

}
