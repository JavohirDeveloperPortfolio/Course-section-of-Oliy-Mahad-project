package uz.oliymahad.courseservice.entity.quequeue;
import lombok.*;
import uz.oliymahad.courseservice.entity.BaseEntity;
import uz.oliymahad.courseservice.entity.course.CourseEntity;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class QueueEntity extends BaseEntity {

    @Column(nullable = false)
    private long userId;

    @Column(nullable = false)
    private Date appliedDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderEnum orderEnum;

    @ManyToOne
    private CourseEntity courseEntity;
}
