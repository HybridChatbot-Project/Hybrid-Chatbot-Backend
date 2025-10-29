package com.hybridchatbot.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "genre", schema = "hybridchatbot")
public class Genre {
    @Id
    private Integer id;
    private String genre;
    @Column(name = "isactive")
    private Integer isActive;
}
