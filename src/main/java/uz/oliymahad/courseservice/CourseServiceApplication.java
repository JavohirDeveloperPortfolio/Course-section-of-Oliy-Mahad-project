package uz.oliymahad.courseservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import uz.oliymahad.courseservice.dto.QueueDto;
import uz.oliymahad.courseservice.entity.course.CourseEntity;
import uz.oliymahad.courseservice.entity.quequeue.QueueEntity;
import uz.oliymahad.courseservice.entity.quequeue.Status;
import uz.oliymahad.courseservice.repository.CourseRepository;
import uz.oliymahad.courseservice.repository.QueueRepository;
import uz.oliymahad.courseservice.service.QueueService;

import java.util.stream.IntStream;

@SpringBootApplication
@EnableFeignClients
public class CourseServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CourseServiceApplication.class, args);
    }


    
}
