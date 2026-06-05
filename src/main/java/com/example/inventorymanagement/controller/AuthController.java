package com.example.inventorymanagement.controller;

import com.example.inventorymanagement.dto.AuthResponse;
import com.example.inventorymanagement.dto.LoginRequest;
import com.example.inventorymanagement.dto.MessageResponse;
import com.example.inventorymanagement.dto.RegisterRequest;
import com.example.inventorymanagement.entity.User;
import com.example.inventorymanagement.security.JwtUtil;
import com.example.inventorymanagement.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<MessageResponse> register(@Valid @RequestBody RegisterRequest request) {
        userService.registerUser(request);
        return ResponseEntity.ok(new MessageResponse("User registered successfully"));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        User user = userService.loginUser(request);
        String token = jwtUtil.generateToken(user.getEmail());
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
