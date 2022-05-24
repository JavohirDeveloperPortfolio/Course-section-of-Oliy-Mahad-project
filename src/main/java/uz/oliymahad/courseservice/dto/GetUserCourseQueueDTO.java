package uz.oliymahad.courseservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetUserCourseQueueDTO {
    private Long userId;
    private Long courseId;
}
