package com.example.foodpanda.repository;

import com.example.foodpanda.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer>
{
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    boolean existsUserByUsernameAndPassword(String username, String password);

    List<User> findAll();

}
