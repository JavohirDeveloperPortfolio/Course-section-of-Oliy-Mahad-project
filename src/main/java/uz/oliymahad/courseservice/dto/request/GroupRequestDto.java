package uz.oliymahad.courseservice.dto.request;

import lombok.Data;

import java.util.Date;

@Data
public class GroupRequestDto {

    private String name ;

    private long membersCount ;

    private String type ;

    private Date startDate ;

    private long courseId ;


}
