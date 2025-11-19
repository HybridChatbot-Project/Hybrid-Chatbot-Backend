package com.hybridchatbot.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageRequest {
    private String message;
    private Integer conversationId;
    private Integer sequence;
    private String sender;
    private String userName;
}
