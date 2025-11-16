package com.hybridchatbot.controllers;

import com.hybridchatbot.repository.ConversationRepository;
import com.hybridchatbot.request.ConversationCloseRequest;
import com.hybridchatbot.response.BaseResponse;
import com.hybridchatbot.services.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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

    @PutMapping("/close-conversation")
    public ResponseEntity<?> updateConversation(@Validated @RequestBody ConversationCloseRequest conversationCloseRequest) {
        try {
            BaseResponse<?> modificado = conversationService.updateConversation(conversationCloseRequest);
            return ResponseEntity.ok().body(modificado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/create-conversation/{id}")
    public ResponseEntity<?> createConversation(@PathVariable("id") Integer id) {
        try {
            BaseResponse<?> creado = conversationService.createConversation(id);
            return ResponseEntity.ok().body(creado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete-conversation/{id}")
    public ResponseEntity<?> deleteConversation(@PathVariable("id") Integer id) {
        try {
            BaseResponse<?> eliminado = conversationService.deleteConversation(id);
            return ResponseEntity.ok().body(eliminado);
        }  catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
