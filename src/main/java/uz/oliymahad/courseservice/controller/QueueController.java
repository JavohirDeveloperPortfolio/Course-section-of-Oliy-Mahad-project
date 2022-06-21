package uz.oliymahad.courseservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import uz.oliymahad.courseservice.dto.response.Response;
import uz.oliymahad.courseservice.dto.response.RestAPIResponse;
import uz.oliymahad.courseservice.dto.request.QueueDto;
import uz.oliymahad.courseservice.service.QueueService;
import uz.oliymahad.dto.request.UsersIDSRequest;

import static uz.oliymahad.courseservice.controller.BaseController.API;


@RequiredArgsConstructor
@RestController
@RequestMapping(API + "/course/queue")
public class QueueController implements BaseController {

    private final QueueService queueService;

//    @GetMapping("/getQueue")
//    public RestAPIResponse getQueue (Pageable pageable) {
//       return queueService.getQueueDetails(pageable);
//    }

    @PostMapping("/add")
    public ResponseEntity<?> addQueue(@RequestBody QueueDto queueDto) {
        RestAPIResponse apiResponse = queueService.add(queueDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @GetMapping()
    public ResponseEntity<?> getList (
          Pageable page
    ) {
        return ResponseEntity.ok(queueService.getList(page));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getQueue (@PathVariable Long id) {
        RestAPIResponse apiResponse = queueService.get(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @PutMapping( "/{id}")
    public ResponseEntity<?> updateQueue (@PathVariable Long id, @RequestBody QueueDto queueDto) {
        RestAPIResponse apiResponse = queueService.edit(id, queueDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiResponse);
    }
    
    @DeleteMapping( "/{id}")
    public ResponseEntity<?> deleteQueue (@PathVariable Long id) {
        RestAPIResponse apiResponse = queueService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @GetMapping("/user/{userId}/course/{courseId}") //userning shu kursda neccinci orinda turganini korsatadi
    public ResponseEntity<?> getUserCourseQueue(
            @PathVariable Long userId,
            @PathVariable Long courseId
    ){
        RestAPIResponse apiResponse = queueService.getUserCourseQueue(userId,courseId);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiResponse);
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
        RestAPIResponse queueByFilter = queueService.getQueueByFilter(userId, gender, status, courseId, appliedDate);
        return ResponseEntity.status(HttpStatus.OK).body(queueByFilter);
    }

    @GetMapping("/list")
    private RestAPIResponse getQueueDetails(
//            @RequestParam(name = "page",required = false, defaultValue = "0") Integer page,
//            @RequestParam(name = "size",required = false, defaultValue = "20") Integer size,
//            @RequestParam(name = "order",required = false,defaultValue = "DESC") String order,
//            @RequestParam(name = "tags",required = false) String[] tags
            Pageable pageable
    ) throws JsonProcessingException {
//        System.out.println("page: " + page +" \nsize: " + size + " \norder: " + order + " \ntags: " +tags);

//        return ResponseEntity.ok(
//                (tags == null) ?
//                        queueService.getQueueDetails(PageRequest.of(page, size)):
//                        queueService.getQueueDetails(PageRequest.of(page, size, Sort.Direction.valueOf(order), tags))
//                );
        return queueService.getQueueDetails(pageable);
    }

    @PostMapping("/test")
    ResponseEntity<?> getUsers(
            @RequestBody UsersIDSRequest usersIDSRequest
    ){
        return ResponseEntity.ok(queueService.getUserDetailsInQueue(usersIDSRequest));
    }

}
