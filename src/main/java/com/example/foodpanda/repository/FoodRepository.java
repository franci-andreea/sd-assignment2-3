package com.example.foodpanda.repository;

import com.example.foodpanda.model.Food;
import com.example.foodpanda.model.FoodCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food, Integer>
{
    Optional<Food> findByName(String name);
    Optional<Food> findByCategory(FoodCategory category);
}
