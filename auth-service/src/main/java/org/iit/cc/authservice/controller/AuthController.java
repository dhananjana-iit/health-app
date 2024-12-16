package org.iit.cc.authservice.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.iit.cc.authservice.service.AuthService;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        if (authService.validateCredentials(username, password)) {
            String token = authService.generateToken(username);
            return ResponseEntity.ok(Map.of("token", token));
        } else {
            return ResponseEntity.status(401).body(Map.of("error", "Invalid credentials"));
        }
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<Map<String, String>> refreshToken(@RequestBody Map<String, String> request) {
        String token = request.get("token");
        if (authService.validateToken(token)) {
            String newToken = authService.generateToken(authService.getUsernameFromToken(token));
            return ResponseEntity.ok(Map.of("token", newToken));
        } else {
            return ResponseEntity.status(401).body(Map.of("error", "Invalid or expired token"));
        }
    }
}

