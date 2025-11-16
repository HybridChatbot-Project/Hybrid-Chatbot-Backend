package com.hybridchatbot.services;

import com.hybridchatbot.models.Conversation;
import com.hybridchatbot.models.Message;
import com.hybridchatbot.repository.ConversationRepository;
import com.hybridchatbot.request.ConversationCloseRequest;
import com.hybridchatbot.response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public BaseResponse<?> updateConversation(ConversationCloseRequest conversationCloseRequest) {
        Conversation modified = conversationRepository.findById(conversationCloseRequest.getConversationId()).orElse(null);
        if (modified == null) {
            throw new RuntimeException("No se encontro el conversation con el id: " + conversationCloseRequest.getConversationId());
        }
        modified.setIsClosed(true);
        modified.setComment(conversationCloseRequest.getUserComment());
        modified.setGrade(conversationCloseRequest.getGrade());
        conversationRepository.save(modified);
        return new BaseResponse<>(200, "Ok", Optional.of(modified));
    }

    public BaseResponse<?> createConversation(Integer userId) {
        Conversation newConversation = new Conversation();
        newConversation.setUserId(userId);
        newConversation.setIsClosed(false);
        newConversation.setIsActive(1);
        newConversation.setCreateDate(LocalDateTime.now());
        conversationRepository.save(newConversation);
        return new BaseResponse<>(200, "Ok", Optional.of(newConversation));
    }

    public BaseResponse<?> deleteConversation(Integer idConversation) {
        conversationRepository.deleteById(idConversation);
        return new BaseResponse<>(200, "Ok", Optional.of("Borrado exitoso"));
    }
}
