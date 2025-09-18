package se331.se331lab093.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class TestController {

    @GetMapping("/test")
    public String test() {
        return "Backend is working!";
    }

    @GetMapping("/health")
    public String health() {
        return "OK";
    }
}
