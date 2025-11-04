package com.hybridchatbot.services;

import com.hybridchatbot.helpers.JwtUtil;
import com.hybridchatbot.models.Genre;
import com.hybridchatbot.models.User;
import com.hybridchatbot.repository.GenreRepository;
import com.hybridchatbot.repository.UserRepository;
import com.hybridchatbot.request.LoginRequest;
import com.hybridchatbot.request.UserRequest;
import com.hybridchatbot.response.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SecurityService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    GenreRepository genreRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final JwtUtil jwtUtil;

    public String login(User user) {
        return jwtUtil.generateToken(user);
    }

    public BaseResponse<?> register(UserRequest userRequest) {
        if (userRepository.existsByEmail(userRequest.getEmail())) {
            throw new RuntimeException("Email ya registrado");
        }

        Genre genre = genreRepository.findById(userRequest.getGender()).orElse(null);
        User newUser = new User();
        newUser.setFirstName(userRequest.getFirstName());
        newUser.setLastName(userRequest.getLastName());
        newUser.setEmail(userRequest.getEmail());
        newUser.setUserPassword(passwordEncoder.encode(userRequest.getPassword()));
        newUser.setGenre(genre);
        newUser.setPhoneNumber(userRequest.getPhoneNumber());
        newUser.setBirthDate(userRequest.getBirthDate());
        newUser.setCreatedDate(LocalDateTime.now());
        newUser.setIsActive(1);
        userRepository.save(newUser);

        return new BaseResponse<>(200, "Registro exitoso", Optional.of(newUser));
    }

    public BaseResponse<?> login(LoginRequest loginRequest) {
        if (userRepository.existsByEmail(loginRequest.getEmail())) {
            User user = userRepository.findByEmail(loginRequest.getEmail());

            if(user.getIsActive()==1 && !passwordEncoder.matches(loginRequest.getPassword(), user.getUserPassword())) {
                throw new RuntimeException("Contraseña incorrecta");
            }

            String token = login(user);
            return new BaseResponse<>(200, "Login exitoso", Optional.of(token));

        } else {
            throw new RuntimeException("Email no encontrado");
        }
    }
}
