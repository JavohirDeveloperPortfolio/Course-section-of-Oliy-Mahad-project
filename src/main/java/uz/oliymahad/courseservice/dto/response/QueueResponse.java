package uz.oliymahad.courseservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class QueueResponse {
    private Long id;
    private String courseName;
    private Long userId;
    private String phoneNumber;
    private String email;
    private String firstName;
    private String lastName;
    private LocalDateTime appliedDate;
    private LocalDateTime endDate;
}
