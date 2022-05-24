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
    String GET_USER_COURSE_QUEUE = "/getUserQueue";
    String GET_USERS_BY_FILTER = "/getUsersByFilter";
}
