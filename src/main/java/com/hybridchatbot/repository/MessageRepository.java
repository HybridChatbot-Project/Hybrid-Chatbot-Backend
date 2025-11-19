package com.hybridchatbot.repository;

import com.hybridchatbot.models.Message;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message,Integer> {
    List<Message> findByConversationId(Integer conversationId);

    @Query
            (value = """
            SELECT m.messagedata FROM message m WHERE m.conversationid = :conversationId ORDER BY m.sequencedata ASC LIMIT 1
""", nativeQuery = true)
    String findFirstLine(@Param("conversationId") Integer conversationId);
}
