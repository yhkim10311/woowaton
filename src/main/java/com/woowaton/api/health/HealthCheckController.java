package com.woowaton.api.health;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    @GetMapping("/")
    public ResponseEntity<Long> healthCheck() { return ResponseEntity.ok(System.currentTimeMillis());}
}
