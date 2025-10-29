package com.hybridchatbot.services;

import com.hybridchatbot.models.Message;
import com.hybridchatbot.repository.MessageRepository;
import com.hybridchatbot.response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public BaseResponse<?> listMessages(Integer conversationId){
        List<Message> messages = messageRepository.findByConversationId(conversationId);
        return new BaseResponse<>(200, "Ok", Optional.of(messages));
    }
}
