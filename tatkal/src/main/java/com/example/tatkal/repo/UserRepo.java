package com.example.tatkal.repo;

import com.example.tatkal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, String> {
    public User findByUserName(String userName);
}
