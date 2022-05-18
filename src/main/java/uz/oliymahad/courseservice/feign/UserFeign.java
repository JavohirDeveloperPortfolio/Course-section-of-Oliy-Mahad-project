package uz.oliymahad.courseservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PatchMapping;

@FeignClient(name = "User-Section-of-Oliy-Mahad")
public interface UserFeign {

    @PatchMapping("/api/user/{id}")
    boolean isExist(Long id);


}
