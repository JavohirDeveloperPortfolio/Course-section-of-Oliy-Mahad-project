package uz.oliymahad.courseservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface BaseController<T> {
    ResponseEntity<?> add(T t);
    ResponseEntity<?> get(long id);
    ResponseEntity<?>list();
    ResponseEntity<?>edit(long id,T t);
    ResponseEntity<?>delete(long id);
}
