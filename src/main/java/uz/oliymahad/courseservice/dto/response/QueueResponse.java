package uz.oliymahad.courseservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.oliymahad.courseservice.entity.quequeue.Status;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
    private Status status;
}
