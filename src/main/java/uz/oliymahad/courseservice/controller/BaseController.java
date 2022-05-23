package uz.oliymahad.courseservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

public interface BaseController {
    String ADD = "/add";
    String GET = "/get";
    String LIST = "/list";
    String UPDATE = "/update";
    String DELETE = "/delete";
    String API = "/api";
}
