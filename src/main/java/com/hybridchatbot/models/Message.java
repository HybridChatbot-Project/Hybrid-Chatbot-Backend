package com.hybridchatbot.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "message", schema = "hybridchatbot")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "messagedata")
    private String message;
    @Column(name = "conversationid")
    private Integer conversationId;
    @Column(name = "sequencedata")
    private Integer sequence;
}
