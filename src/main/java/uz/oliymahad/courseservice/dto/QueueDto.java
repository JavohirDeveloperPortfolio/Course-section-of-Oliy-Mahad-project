package uz.oliymahad.courseservice.dto;

import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueueDto {
    private long userId;
    private long courseId;
    private Date appliedDate;
}
