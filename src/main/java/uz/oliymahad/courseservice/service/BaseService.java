package uz.oliymahad.courseservice.service;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import uz.oliymahad.courseservice.dto.response.ApiResponse;

/**
 * @param <D> Dto
 * @param <K> Id Long
 * @param <E> Id Long
 * @param <P> Pageable pageable
 */

@Component
public interface BaseService<D,K,E,P> {

    ApiResponse<Void> add(D d);
    ApiResponse<Page<E>> getList(P p);
    ApiResponse<D> get(K id);
    ApiResponse<Void> delete(K id);
    ApiResponse<Void> edit(K id, D d);

}
