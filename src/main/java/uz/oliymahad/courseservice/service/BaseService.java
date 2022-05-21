package uz.oliymahad.courseservice.service;
import org.springframework.stereotype.Component;
import uz.oliymahad.courseservice.dto.ApiResponse;

@Component
public interface BaseService<T> {

    ApiResponse add(T t);
    ApiResponse getList(int age, int pageSize,long CourseId);
    ApiResponse get(long id);
    ApiResponse delete(long id);
    ApiResponse edit(long id, T t);
}
