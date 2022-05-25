package com.example.foodpanda.repository;

import com.example.foodpanda.model.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AdministratorRepository extends JpaRepository<Administrator, Integer>
{
    Optional<Administrator> findByEmail(String email);
    Optional<Administrator> findByUsername(String username);
    Optional<Administrator> findByRestaurantName(String restaurantName);
    boolean existsAdministratorByUsernameAndPassword(String username, String password); //works

    List<Administrator> findAll();
}
