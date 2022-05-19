package uz.oliymahad.courseservice.service;
import uz.oliymahad.courseservice.dto.ApiResponse;


public interface BaseService<T> {

    ApiResponse add(T t);
    ApiResponse getList();
    ApiResponse get(long id);
    ApiResponse delete(long id);
    ApiResponse edit(long id, T t);
}
