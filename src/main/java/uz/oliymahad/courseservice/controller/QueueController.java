package uz.oliymahad.courseservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.oliymahad.courseservice.dto.ApiResponse;
import uz.oliymahad.courseservice.dto.QueueDto;
import uz.oliymahad.courseservice.service.QueueService;

import javax.persistence.StoredProcedureParameter;

@RestController
@RequestMapping("/api/queue")
@RequiredArgsConstructor
public class QueueController implements BaseController<QueueDto> {

    private final QueueService queueService;

    @PostMapping("/add")
    @Override
    public ResponseEntity<?> add(@RequestBody  QueueDto queueDto) {
        ApiResponse apiResponse = queueService.add(queueDto);
        if (apiResponse.isStatus())
            return ResponseEntity.ok(apiResponse);

        return ResponseEntity.status(409).body(apiResponse);
    }

    @GetMapping("/get/{id}")
    @Override
    public ResponseEntity<?> get(@PathVariable long id) {
        ApiResponse apiResponse = queueService.get(id);
        if (apiResponse.isStatus())
            return ResponseEntity.ok(apiResponse);

        return ResponseEntity.status(409).body(apiResponse);
    }

    @GetMapping("/list/{age}/{pageSize}/{id}")
    @Override
    public ResponseEntity<?> list(@PathVariable int age, @PathVariable int pageSize,@PathVariable long id) {
        return ResponseEntity.ok(queueService.getList(age,pageSize,id));
    }

    @PutMapping("/edit/{id}")
    @Override
    public ResponseEntity<?> edit(@PathVariable  long id ,QueueDto queueDto) {
        ApiResponse apiResponse = queueService.edit(id,queueDto);
        if (apiResponse.isStatus())
            return ResponseEntity.ok(apiResponse);

        return ResponseEntity.status(409).body(apiResponse);
    }

    @DeleteMapping("/delete/{id}")
    @Override
    public ResponseEntity<?> delete(@PathVariable  long id) {
        return ResponseEntity.ok(queueService.delete(id));
    }

    @GetMapping("list/{userId}")
    public ResponseEntity<?> getUserQueues(@PathVariable long userId){
        return ResponseEntity.ok(queueService.getUserQueues(userId));
    }
}
