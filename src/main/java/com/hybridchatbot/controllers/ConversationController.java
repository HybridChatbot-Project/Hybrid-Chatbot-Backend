package com.hybridchatbot.controllers;

import com.hybridchatbot.repository.ConversationRepository;
import com.hybridchatbot.response.BaseResponse;
import com.hybridchatbot.services.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/conversation")
public class ConversationController {
    @Autowired
    private ConversationService conversationService;

    @GetMapping("/list-by-user/{id}")
    public ResponseEntity<?> listByUser(@PathVariable("id") Integer id) {
        try {
            BaseResponse<?> listado = conversationService.listConversations(id);
            return ResponseEntity.ok().body(listado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
