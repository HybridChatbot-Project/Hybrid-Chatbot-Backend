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

    @Column(name = "userpassword")
    private String userPassword;
    @Column(name = "lastname")
    private String lastName;
    @Column(name="firstname")
    private String firstName;
    private String email;
    @Column(name = "phonenumber")
    private Integer phoneNumber;
    @OneToOne()
    @JoinColumn(name = "idgenre", referencedColumnName = "id", nullable = false)
    private Genre genre;
    @Column(name = "birthdate")
    private LocalDateTime birthDate;
    @Column(name = "createdate")
    private LocalDateTime createdDate;
    @Column(name = "modifieddate")
    private LocalDateTime updatedDate;
    @Column(name = "isactive")
    private Integer isActive;
    @Column(name = "profileimage")
    private String profileImage;
    @Column(name = "publicimageid")
    private String publicImageId;
}
