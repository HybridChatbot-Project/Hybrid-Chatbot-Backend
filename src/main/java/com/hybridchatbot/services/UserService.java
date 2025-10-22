package com.hybridchatbot.services;

import com.hybridchatbot.models.User;
import com.hybridchatbot.repository.UserRepository;
import com.hybridchatbot.request.UserRequest;
import com.hybridchatbot.response.BaseResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public BaseResponse<?> listUsers() {
        List<User> users = userRepository.findAllByIsActive(1);
        return new BaseResponse<>(200, "OK", Optional.of(users));
    }

    @Transactional
    public BaseResponse<?> updateUser(Integer id, UserRequest userRequest) {
        User existed =  userRepository.findById(id).orElse(null);
        if (existed == null) {
            return new BaseResponse<>(400, "User not found", null);
        }
        existed.setFirstName(userRequest.getFirstName());
        existed.setLastName(userRequest.getLastName());
        existed.setEmail(userRequest.getEmail());
        existed.setUpdatedDate(LocalDateTime.now());

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
}
