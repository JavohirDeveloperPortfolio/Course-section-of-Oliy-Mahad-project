package uz.oliymahad.courseservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.oliymahad.courseservice.dto.ApiResponse;
import uz.oliymahad.courseservice.dto.group.GroupResponseDto;
import uz.oliymahad.courseservice.dto.group.GroupRequestDto;
import uz.oliymahad.courseservice.entity.group.GroupEntity;
import uz.oliymahad.courseservice.service.GroupService;

@RestController
@RequestMapping("/api/group")
@RequiredArgsConstructor
public class GroupController implements BaseController{

    private final GroupService groupService ;

    @PostMapping("/add")
    public ResponseEntity<?> add (@RequestBody GroupRequestDto groupRequestDto) {
        ApiResponse<Void> apiResponse = groupService.addGroup(groupRequestDto);
        return ResponseEntity.status(apiResponse.isStatus() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    @GetMapping("/getAllGroups")
    public ResponseEntity<?> getAllGroups () {
        ApiResponse<Void> apiResponse = groupService.getAllGroups();
        return ResponseEntity.status(apiResponse.isStatus() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    @GetMapping("/getGroupPage")
    public ResponseEntity<Page<GroupEntity>> getGroupPage(@RequestParam int pageNumber) {
        Page<GroupEntity> groupPage = groupService.getGroupPage(pageNumber);
        return ResponseEntity.ok(groupPage);
    }

    @GetMapping("/getGroupPage")
    public ResponseEntity<Page<GroupEntity>> getGroupPage(@RequestParam int pageNumber, int pageSize) {
        Page<GroupEntity> groupPage = groupService.getGroupPage(pageNumber, pageSize);
        return ResponseEntity.ok(groupPage);
    }

    @GetMapping("/getGroupPage")
    public Page<GroupResponseDto> getGroupPage(Pageable page) {
        return groupService.getGroupPage(page);
    }




}
