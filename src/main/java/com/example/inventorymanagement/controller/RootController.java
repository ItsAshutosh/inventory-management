package com.example.inventorymanagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class RootController {

    @GetMapping({"/", "/health", "/api/health"})
    public ResponseEntity<Map<String, Object>> root() {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("name", "Inventory Management REST API");
        response.put("version", "1.0.0");
        response.put("status", "UP");
        response.put("description", "Spring Boot 3 REST API with JWT Authentication, Role-based Access Control, and Product Management.");
        response.put("documentation", "/swagger-ui.html");

        Map<String, String> publicEndpoints = new LinkedHashMap<>();
        publicEndpoints.put("API Root", "GET /");
        publicEndpoints.put("Swagger UI", "GET /swagger-ui.html");
        publicEndpoints.put("Register", "POST /auth/register");
        publicEndpoints.put("Login", "POST /auth/login");
        response.put("publicEndpoints", publicEndpoints);

        Map<String, String> protectedEndpoints = new LinkedHashMap<>();
        protectedEndpoints.put("Categories", "GET, POST, PUT, DELETE /categories");
        protectedEndpoints.put("Products", "GET, POST, PUT, DELETE /products");
        protectedEndpoints.put("Product Search", "GET /products/search?name={name}");
        protectedEndpoints.put("Low Stock Alert", "GET /products/low-stock (ADMIN)");
        protectedEndpoints.put("Update Stock", "PATCH /products/{id}/stock?quantity={qty} (ADMIN)");
        response.put("protectedEndpoints", protectedEndpoints);

        return ResponseEntity.ok(response);
    }
}
