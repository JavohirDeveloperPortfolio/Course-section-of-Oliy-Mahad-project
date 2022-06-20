package uz.oliymahad.courseservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.oliymahad.courseservice.dto.response.CourseSectionDto;

import java.util.List;

@RestController
@RequestMapping("/api/v1/feign")
public class FeignController {


    @GetMapping("/course")
    public List<CourseSectionDto> course(){
        return null;
    }
}
