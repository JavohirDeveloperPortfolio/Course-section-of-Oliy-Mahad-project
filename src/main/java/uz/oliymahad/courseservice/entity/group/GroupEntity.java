package uz.oliymahad.courseservice.entity.group;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import uz.oliymahad.courseservice.entity.BaseEntity;

import javax.persistence.Entity;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class GroupEntity extends BaseEntity {

    private String name;

    @CreatedDate
    private Date createdDate ;

}
