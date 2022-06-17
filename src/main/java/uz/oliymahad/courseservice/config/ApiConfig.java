package uz.oliymahad.courseservice.config;


import org.modelmapper.ModelMapper;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiConfig {

    @Bean
    public ModelMapper modelMapper () {
        return new ModelMapper();
    }
}
