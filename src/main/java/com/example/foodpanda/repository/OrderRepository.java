package com.example.foodpanda.repository;

import com.example.foodpanda.model.Order;
import com.example.foodpanda.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer>
{
    Optional<Order> findByStatus(OrderStatus status);
}
