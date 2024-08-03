package com.fast.pay.read.controller;

import com.fast.pay.read.model.AppStatus;
import com.fast.pay.read.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/redis")
@RequiredArgsConstructor
@Slf4j
public class RedisController {
    private final UserService userService;

    @GetMapping("/{id}/detail")
    public ResponseEntity<AppStatus> getById(@PathVariable("id") UUID id) {

        return ResponseEntity.ok(userService.getById(id));
    }

    @GetMapping("/users")
    public ResponseEntity<AppStatus> findAll() {

        return ResponseEntity.ok(userService.getAll());
    }
    
}
