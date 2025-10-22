package com.hybridchatbot.repository;

import com.hybridchatbot.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecurityRepository extends JpaRepository<User,Integer> {
}
