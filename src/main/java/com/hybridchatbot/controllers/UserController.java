package com.hybridchatbot.controllers;

import com.hybridchatbot.models.User;
import com.hybridchatbot.request.UserRequest;
import com.hybridchatbot.response.BaseResponse;
import com.hybridchatbot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public ResponseEntity<?> listUsers() {
        try {
            BaseResponse<?> listado = userService.listUsers();
            return ResponseEntity.ok().body(listado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/find-user/{id}")
    public ResponseEntity<?> findUserById(@PathVariable("id") Integer id) {
        try {
            BaseResponse<?> user = userService.getUserById(id);
            return ResponseEntity.ok().body(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Integer id, @Validated @RequestBody UserRequest userRequest) {
        try {
            BaseResponse<?> actualizacion = userService.updateUser(id, userRequest);
            return ResponseEntity.ok().body(actualizacion);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Integer id) {
        try {
            BaseResponse<?> actualizacion = userService.deleteUser(id);
            return ResponseEntity.ok().body(actualizacion);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
