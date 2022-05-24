package uz.oliymahad.courseservice.service;
import org.springframework.stereotype.Component;
import uz.oliymahad.courseservice.dto.ApiResponse;

import java.util.List;

/**
 * @param <D> Dto
 * @param <K> Id Long
 * @param <E> Id Long
 */

@Component
public interface BaseService<D,K,E> {

    ApiResponse<Void> add(D d);
    ApiResponse<List<E>> getList();
    ApiResponse<D> get(K id);
    ApiResponse<Void> delete(K id);
    ApiResponse<Void> edit(K id, D d);
}
