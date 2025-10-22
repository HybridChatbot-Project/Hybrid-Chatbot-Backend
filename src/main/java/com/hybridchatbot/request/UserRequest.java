package com.hybridchatbot.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRequest {
    String firstName;
    String lastName;
    String email;
    LocalDateTime birthDate;
    Integer phoneNumber;
}
