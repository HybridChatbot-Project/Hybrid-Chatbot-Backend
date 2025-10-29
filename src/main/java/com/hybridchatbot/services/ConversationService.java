package com.hybridchatbot.services;

import com.hybridchatbot.models.Conversation;
import com.hybridchatbot.models.Message;
import com.hybridchatbot.repository.ConversationRepository;
import com.hybridchatbot.response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConversationService {
    @Autowired
    private ConversationRepository conversationRepository;

    public BaseResponse<?> listConversations(Integer userId) {
        List<Conversation> conversations = conversationRepository.findByUserId(userId);
        return new BaseResponse<>(200, "Ok", Optional.of(conversations));
    }
}
