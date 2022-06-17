package uz.oliymahad.courseservice.feign;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import uz.oliymahad.courseservice.config.LoadBalancerConfigurer;
import uz.oliymahad.dto.request.UsersIDSRequest;
import uz.oliymahad.dto.response.UserDataResponse;
import uz.oliymahad.model.entity.UserEntity;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "localhost",url = "http://localhost:8080")
//@LoadBalancerClients(defaultConfiguration = {LoadBalancerConfigurer.class})
public interface UserFeign {

    @GetMapping("/api/v1/user/queue")
    Optional<?> getUserByPhoneNumber(String phoneNumber);

    @PostMapping("api/v1/user/feign")
    List<UserDataResponse> getUsers(@RequestBody UsersIDSRequest usersIDSRequest);
    
}
