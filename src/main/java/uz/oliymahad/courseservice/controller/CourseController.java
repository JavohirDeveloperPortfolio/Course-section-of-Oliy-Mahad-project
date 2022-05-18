package uz.oliymahad.courseservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.oliymahad.courseservice.dto.ApiResponse;
import uz.oliymahad.courseservice.dto.CourseDto;
import uz.oliymahad.courseservice.service.CourseService;

@RestController
@RequestMapping("/api/course")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping("/add")
    public ResponseEntity<?> addCourse (@RequestBody CourseDto courseDto) {
        ApiResponse apiResponse = courseService.addCourse(courseDto);
        if (apiResponse.isStatus()) {
            return ResponseEntity.ok(apiResponse);
        }
        return ResponseEntity.status(409).body(apiResponse);
    }

    @GetMapping("/list")
    public ResponseEntity<?> getCourseList () {
        return ResponseEntity.ok(courseService.getCourses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCourse (@PathVariable Long id) {
        ApiResponse apiResponse = courseService.getCourseById(id);
        if (apiResponse.isStatus()) {
            return ResponseEntity.ok(apiResponse);
        }
        return ResponseEntity.status(409).body(apiResponse);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable Long id, @RequestBody CourseDto courseDto) {
        ApiResponse apiResponse = courseService.updateCourse(id, courseDto);
        if (apiResponse.isStatus()) {
            return ResponseEntity.ok(apiResponse);
        }
        return ResponseEntity.status(409).body(apiResponse);
    }

    @DeleteMapping("/deleteCourse/{id}")
    public ResponseEntity<?> deleteCourse (@PathVariable Long id) {
        ApiResponse apiResponse = courseService.deleteCourse(id);
        if (apiResponse.isStatus()) {
            return ResponseEntity.ok(apiResponse);
        }
        return ResponseEntity.status(409).body(apiResponse);
    }
}
