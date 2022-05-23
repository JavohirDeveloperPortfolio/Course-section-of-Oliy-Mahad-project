package uz.oliymahad.courseservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.oliymahad.courseservice.dto.ApiResponse;
import uz.oliymahad.courseservice.dto.QueueDto;
import uz.oliymahad.courseservice.service.QueueService;

import static uz.oliymahad.courseservice.controller.BaseController.API;


@RestController
@RequestMapping(API +"/queue")
@RequiredArgsConstructor
public class QueueController implements BaseController {

    private final QueueService queueService;

    @PostMapping(ADD)
    public ResponseEntity<?> addQueue(@RequestBody QueueDto queueDto) {
        ApiResponse<Void> apiResponse = queueService.add(queueDto);
        return ResponseEntity.status(apiResponse.isStatus() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    @GetMapping(LIST)
    public ResponseEntity<?> getList () {
        return ResponseEntity.ok(queueService.getList());
    }

    @GetMapping(GET + "/{id}")
    public ResponseEntity<?> getQueue (@PathVariable Long id) {
        ApiResponse<QueueDto> apiResponse = queueService.get(id);
        return ResponseEntity.status(apiResponse.isStatus() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @PutMapping(UPDATE + "/{id}")
    public ResponseEntity<?> updateQueue (@PathVariable Long id, @RequestBody QueueDto queueDto) {
        ApiResponse<Void> apiResponse = queueService.edit(id, queueDto);
        return ResponseEntity.status(apiResponse.isStatus() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @DeleteMapping(DELETE + "/{id}")
    public ResponseEntity<?> deleteQueue (@PathVariable Long id) {
        ApiResponse<Void> apiResponse = queueService.delete(id);
        return ResponseEntity.status(apiResponse.isStatus() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiResponse);
    }

}
