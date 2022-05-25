package uz.oliymahad.courseservice.dto.group;

import lombok.Data;

@Data
public class GroupRequestDto {

    private String name;

    private String type;

    private long membersCount ;

    private long courseId ;

}
