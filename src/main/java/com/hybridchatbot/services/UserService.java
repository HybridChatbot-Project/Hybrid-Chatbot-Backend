package com.hybridchatbot.services;

import com.hybridchatbot.models.Genre;
import com.hybridchatbot.models.User;
import com.hybridchatbot.repository.GenreRepository;
import com.hybridchatbot.repository.UserRepository;
import com.hybridchatbot.request.UserImageRequest;
import com.hybridchatbot.request.UserRequest;
import com.hybridchatbot.response.BaseResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GenreRepository genreRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public BaseResponse<?> listUsers() {
        List<User> users = userRepository.findAllByIsActive(1);
        return new BaseResponse<>(200, "OK", Optional.of(users));
    }

    public BaseResponse<?> getUserById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        return new BaseResponse<>(200, "OK", Optional.of(user));
    }

    @Transactional
    public BaseResponse<?> updateUser(Integer id, UserRequest userRequest) {
        User existed =  userRepository.findById(id).orElse(null);
        if (existed == null) {
            return new BaseResponse<>(400, "User not found", null);
        }
        Genre genre = genreRepository.findById(userRequest.getGender()).orElse(null);
        existed.setFirstName(userRequest.getFirstName());
        existed.setLastName(userRequest.getLastName());
        existed.setEmail(userRequest.getEmail());
        existed.setUpdatedDate(LocalDateTime.now());
        existed.setGenre(genre);
        existed.setPhoneNumber(userRequest.getPhoneNumber());
        existed.setBirthDate(userRequest.getBirthDate());
        if (userRequest.getPassword() != null) {
            existed.setUserPassword(passwordEncoder.encode(userRequest.getPassword()));
        }

        User modified = userRepository.save(existed);
        return new BaseResponse<>(200, "User updated correctly", Optional.of(modified));
    }

    @Transactional
    public BaseResponse<?> deleteUser(Integer id) {
        User existed =  userRepository.findById(id).orElse(null);
        if (existed == null) {
            return new BaseResponse<>(400, "User not found", null);
        }
        existed.setIsActive(0);
        User deleted = userRepository.save(existed);

        return new BaseResponse<>(200, "User deleted correctly", Optional.of(deleted));
    }

    public BaseResponse<?> updateImage(UserImageRequest userImageRequest) {
        User existed =  userRepository.findById(userImageRequest.getUserId()).orElse(null);
        if (existed == null) {
            throw new RuntimeException("User not found");
        }
        existed.setProfileImage(userImageRequest.getImage());
        existed.setPublicImageId(userImageRequest.getImageId());
        userRepository.save(existed);
        return new BaseResponse<>(200, "User updated correctly", Optional.of(existed));
    }
}
