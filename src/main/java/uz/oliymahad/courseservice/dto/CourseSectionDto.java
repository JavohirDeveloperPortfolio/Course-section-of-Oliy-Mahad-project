package uz.oliymahad.courseservice.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CourseSectionDto {
    private String name;
    private String description;
    private double price;
    private float duration;
}
