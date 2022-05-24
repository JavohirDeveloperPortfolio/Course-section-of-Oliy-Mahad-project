package uz.oliymahad.courseservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CourseDto {

    private String name;
    private String description;
    private double price;
    private float duration;
    private Long adminId;
}
