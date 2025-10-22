package com.hybridchatbot.repository;

import com.hybridchatbot.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findAllByIsActive(Integer isActive);
}
