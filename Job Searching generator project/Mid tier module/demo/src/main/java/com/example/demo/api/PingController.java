package com.example.demo.api;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class PingController {
    @GetMapping("/ping")
    public String ping() { return "ok"; }
}
