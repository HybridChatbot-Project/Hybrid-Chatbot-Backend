package com.hybridchatbot.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user", schema = "hybridchatbot")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String password;
    private String lastName;
    private String firstName;
    private String email;
    private Integer phoneNumber;
    @OneToOne()
    @JoinColumn(name = "idgenre", referencedColumnName = "id", nullable = false)
    private Genre genre;
    private LocalDateTime birthDate;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private Integer isActive;
}
