package com.hybridchatbot.controllers;

import com.hybridchatbot.models.Message;
import com.hybridchatbot.request.MessageRequest;
import com.hybridchatbot.response.BaseResponse;
import com.hybridchatbot.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @GetMapping("/find-by-conversation/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Integer id) {
        try {
            BaseResponse<?> listado = messageService.listMessages(id);
            return ResponseEntity.ok().body(listado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/find-first-line/{id}")
    public ResponseEntity<?> findFirstLine(@PathVariable("id") Integer id) {
        try {
            String line = messageService.findFirstLine(id);
            return ResponseEntity.ok().body(line);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/process-message")
    public ResponseEntity<?> processMessage(@RequestBody MessageRequest messageRequest) {
        try {
            BaseResponse<?> respuesta = messageService.processMessage(messageRequest);
            return ResponseEntity.ok().body(respuesta);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
