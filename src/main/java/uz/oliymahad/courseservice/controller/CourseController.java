package uz.oliymahad.courseservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.oliymahad.courseservice.dto.ApiResponse;
import uz.oliymahad.courseservice.dto.CourseDto;
import uz.oliymahad.courseservice.service.CourseService;

import static uz.oliymahad.courseservice.controller.BaseController.API;

@RestController
@RequestMapping(API+"/course")
@RequiredArgsConstructor
public class CourseController implements BaseController{

    private final CourseService courseService;

    @PostMapping(ADD)
    public ResponseEntity<?> addCourse (@RequestBody CourseDto courseDto) {
        ApiResponse<Void> apiResponse = courseService.add(courseDto);
        return ResponseEntity.status(apiResponse.isStatus() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @GetMapping(LIST)
    public ResponseEntity<?> getCourseList (Pageable pageable) {
        return ResponseEntity.ok(courseService.getList(pageable));
    }

    @GetMapping(GET + "/{id}")
    public ResponseEntity<?> getCourse (@PathVariable Long id) {
        ApiResponse<CourseDto> apiResponse = courseService.get(id);
        return ResponseEntity.status(apiResponse.isStatus() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @GetMapping(GET + "{name}")
    public ResponseEntity<?> getCourseByName (@RequestParam(name = "name") String name) {
        ApiResponse<CourseDto> apiResponse = courseService.getByName(name);
        return ResponseEntity.status(apiResponse.isStatus() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @PutMapping(UPDATE+"/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable Long id, @RequestBody CourseDto courseDto) {
        ApiResponse<Void> apiResponse = courseService.edit(id, courseDto);
        return ResponseEntity.status(apiResponse.isStatus() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @DeleteMapping(DELETE+"/{id}")
    public ResponseEntity<?> deleteCourse (@PathVariable Long id) {
        ApiResponse<Void> apiResponse = courseService.delete(id);
        return ResponseEntity.status(apiResponse.isStatus() ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND).body(apiResponse);
    }
}