package com.hybridchatbot.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "conversation", schema = "hybridchatbot")
public class Conversation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "createdate")
    private LocalDateTime createDate;
    @Column(name = "isactive")
    private Integer isActive;
    @Column(name = "userid")
    private Integer userId;
    private Integer grade;
    @Column(name = "usercomment")
    private String comment;
    @Column(name = "isclosed")
    private Boolean isClosed;
}
