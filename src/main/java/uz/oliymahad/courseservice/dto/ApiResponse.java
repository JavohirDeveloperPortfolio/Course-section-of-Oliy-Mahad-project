package uz.oliymahad.courseservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ApiResponse<T> {

    private String message;
    private boolean status;
    private T data;


    public ApiResponse(String message, boolean status) {
        this.message = message;
        this.status = status;
    }
}
