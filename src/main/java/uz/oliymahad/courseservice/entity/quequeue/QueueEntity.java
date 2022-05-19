package uz.oliymahad.courseservice.entity.quequeue;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;
import uz.oliymahad.courseservice.entity.BaseEntity;
import uz.oliymahad.courseservice.entity.course.CourseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class QueueEntity extends BaseEntity {

    @Column(nullable = false)
    private long userId;

    @Column(nullable = false)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime appliedDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderEnum orderEnum;

    @ManyToOne
    private CourseEntity courseEntity;
}
