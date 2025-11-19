package com.hybridchatbot.services;

import com.hybridchatbot.models.Message;
import com.hybridchatbot.repository.MessageRepository;
import com.hybridchatbot.request.MessageRequest;
import com.hybridchatbot.response.BaseResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    @Value("${flask.chatbot-url}")
    private String urlChatbot;
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    public BaseResponse<?> listMessages(Integer conversationId){
        List<Message> messages = messageRepository.findByConversationId(conversationId);
        return new BaseResponse<>(200, "Ok", Optional.of(messages));
    }

    public String findFirstLine(Integer conversationId){
        return messageRepository.findFirstLine(conversationId);
    }

    @Transactional
    public BaseResponse<?> processMessage(MessageRequest messageRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<MessageRequest> entity = new HttpEntity<>(messageRequest, headers);
        ResponseEntity<Message> response = restTemplateBuilder.build().postForEntity(urlChatbot, entity, Message.class);

        Message newMessage = response.getBody();

        Message userMessage = new Message();
        userMessage.setMessage(messageRequest.getMessage());
        userMessage.setConversationId(messageRequest.getConversationId());
        userMessage.setSequence(messageRequest.getSequence());
        userMessage.setSenderType("user");
        messageRepository.save(userMessage);

        Message botMessage = new Message();
        if (newMessage.getMessage() == null || newMessage.getMessage().isEmpty()) {
            throw new RuntimeException("Something went wrong");
        }
        botMessage.setMessage(newMessage.getMessage());
        botMessage.setConversationId(messageRequest.getConversationId());
        botMessage.setSequence(newMessage.getSequence());
        botMessage.setSenderType(newMessage.getSenderType());
        messageRepository.save(botMessage);

        return  new BaseResponse<>(200, "Ok", Optional.of(botMessage));
    }
}
