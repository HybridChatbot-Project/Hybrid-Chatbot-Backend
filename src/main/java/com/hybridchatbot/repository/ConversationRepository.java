package com.hybridchatbot.repository;

import com.hybridchatbot.models.Conversation;
import com.hybridchatbot.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConversationRepository extends JpaRepository<Conversation,Integer> {
    List<Conversation> findByUserId(Integer userId);
}
