package uz.oliymahad.courseservice.entity.group;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import uz.oliymahad.courseservice.entity.BaseEntity;
import uz.oliymahad.courseservice.entity.course.CourseEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class GroupEntity extends BaseEntity {

    private String name;

    private int membersCount ;

    @CreatedDate
    private Date createdDate ;

    @ManyToOne
    private CourseEntity courseId ;

}
