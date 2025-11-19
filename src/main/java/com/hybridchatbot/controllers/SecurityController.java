package com.hybridchatbot.controllers;

import com.hybridchatbot.request.LoginRequest;
import com.hybridchatbot.request.UserRequest;
import com.hybridchatbot.response.BaseResponse;
import com.hybridchatbot.services.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class SecurityController {
    @Autowired
    SecurityService securityService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRequest userRequest) {
        try {
            BaseResponse<?> response = securityService.register(userRequest);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        try {
            BaseResponse<?> response = securityService.login(loginRequest);
            return ResponseEntity.ok().body(response);
        }  catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
