package com.hybridchatbot.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConversationCloseRequest {
    private Integer conversationId;
    private String userComment;
    private Integer grade;
}
