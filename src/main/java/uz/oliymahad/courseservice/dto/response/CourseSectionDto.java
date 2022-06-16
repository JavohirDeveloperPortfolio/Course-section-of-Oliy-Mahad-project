package uz.oliymahad.courseservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CourseSectionDto {
    private Long id;
    private String name;
    private String description;
    private double price;
    private float duration;
}
