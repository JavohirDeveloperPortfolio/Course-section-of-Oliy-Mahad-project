package uz.oliymahad.courseservice.entity.course;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.oliymahad.courseservice.entity.BaseEntity;

import javax.persistence.Entity;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class CourseEntity extends BaseEntity {

    private String name;

    private String description;

    private double price;

    private float duration;

    private Long AdminId;
}
