package com.javaweb.project.controller;

import com.javaweb.project.dto.request.LoginRequest;
import com.javaweb.project.dto.request.RegisterRequest;
import com.javaweb.project.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest req) {
        authService.addAccount(req);
        return ResponseEntity.ok("Dang ky thanh cong");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest req) {
        String token = authService.loginAcccount(req);
        return ResponseEntity.ok(token);
    }
}


//eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJVU0VSIl0sInN1YiI6Im5ndXllbnZhbmMiLCJqdGkiOiI0IiwiaWF0IjoxNzYyMjE5NjYzLCJleHAiOjE3NjIyMjMyNjN9.avNW3EnfaQ0bIxFRN1QbUrEsuzwm5q_Q6_ZBCLhjBJo
