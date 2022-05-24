package uz.oliymahad.courseservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FilterQueueDTO {
    private Long courseId;
    private String status;
    private String gender;
    private Long limit;

}
