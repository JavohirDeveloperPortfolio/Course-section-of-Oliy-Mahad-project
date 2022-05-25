package uz.oliymahad.courseservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.oliymahad.courseservice.dto.ApiResponse;
import uz.oliymahad.courseservice.dto.CourseDto;
import uz.oliymahad.courseservice.dto.group.GroupRequestDto;
import uz.oliymahad.courseservice.service.GroupService;

import java.util.List;

@RestController
@RequestMapping("/api/group")
@RequiredArgsConstructor
public class GroupController implements BaseController{

    private final GroupService groupService ;

    @PostMapping("/add")
    public ResponseEntity<?> add (@RequestBody GroupRequestDto groupRequestDto) {
        ApiResponse<Void> apiResponse = groupService.add(groupRequestDto);
        return ResponseEntity.status(apiResponse.isStatus() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }


}
