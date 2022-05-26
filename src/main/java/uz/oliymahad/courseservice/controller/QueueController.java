package uz.oliymahad.courseservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.oliymahad.courseservice.dto.ApiResponse;
import uz.oliymahad.courseservice.dto.FilterQueueForGroupsDTO;
import uz.oliymahad.courseservice.dto.QueueDto;
import uz.oliymahad.courseservice.entity.quequeue.QueueEntity;
import uz.oliymahad.courseservice.service.QueueService;

import java.util.List;


@RestController
@RequestMapping("/queues")
@RequiredArgsConstructor
public class QueueController implements BaseController {

    private final QueueService queueService;

    @PostMapping()
    public ResponseEntity<?> addQueue(@RequestBody QueueDto queueDto) {
        ApiResponse<Void> apiResponse = queueService.add(queueDto);
        return ResponseEntity.status(apiResponse.isStatus() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @GetMapping()
    public ResponseEntity<?> getList (
          Pageable page
    ) {
        return ResponseEntity.ok(queueService.getList(page));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getQueue (@PathVariable Long id) {
        ApiResponse<QueueDto> apiResponse = queueService.get(id);
        return ResponseEntity.status(apiResponse.isStatus() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @PutMapping( "/{id}")
    public ResponseEntity<?> updateQueue (@PathVariable Long id, @RequestBody QueueDto queueDto) {
        ApiResponse<Void> apiResponse = queueService.edit(id, queueDto);
        return ResponseEntity.status(apiResponse.isStatus() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiResponse);
    }



    @DeleteMapping( "/{id}")
    public ResponseEntity<?> deleteQueue (@PathVariable Long id) {
        ApiResponse<Void> apiResponse = queueService.delete(id);
        return ResponseEntity.status(apiResponse.isStatus() ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @GetMapping("/user/{userId}/course/{courseId}") //userning shu kursda neccinci orinda turganini korsatadi
    public ResponseEntity<?> getUserCourseQueue(
            @PathVariable Long userId,
            @PathVariable Long courseId
    ){
        ApiResponse<List<Long>> apiResponse = queueService.getUserCourseQueue(userId,courseId);
        return ResponseEntity.status(apiResponse.isStatus() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiResponse);
    }
//
//    @GetMapping(GET_USERS_BY_FILTER)
//    public ResponseEntity<?> getUsersByFilter(@RequestBody FilterQueueForGroupsDTO filterQueueDTO){
//        ApiResponse<List<Long>> apiResponse = queueService.getUsersByFilter(filterQueueDTO);
//        return ResponseEntity.status(apiResponse.isStatus() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiResponse);
//    }

    @GetMapping(GET_QUEUES_BY_FILTER)
    public ResponseEntity<?> getQueuesByFilter(
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) String gender,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Long courseId,
            @RequestParam(required = false) String appliedDate
    ){
        ApiResponse<List<QueueEntity>> queueByFilter = queueService.getQueueByFilter(userId, gender, status, courseId, appliedDate);
        return ResponseEntity.status(HttpStatus.OK).body(queueByFilter);
    }





}
