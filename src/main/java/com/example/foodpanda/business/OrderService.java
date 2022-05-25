package com.example.foodpanda.business;

import com.example.foodpanda.model.Food;
import com.example.foodpanda.model.Order;
import com.example.foodpanda.model.OrderStatus;
import com.example.foodpanda.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService
{
    private final OrderRepository orderRepository;

    /**
     * constructor for order service class
     * @param orderRepository - the repository that simulates the orders table from the database
     */
    @Autowired
    public OrderService(OrderRepository orderRepository)
    {
        this.orderRepository = orderRepository;
    }

    /**
     * method to create a new order
     * @param foods - the list of foods that were added to the cart and then ordered
     * @param totalPrice - the total value of the foods' prices
     * DEFAULT STATUS FOR A NEW ORDER - PENDING
     */
    public void makeANewOrder(List<Food> foods, Double totalPrice)
    {
        Order newOrder = new Order(foods, totalPrice, OrderStatus.PENDING);
        this.orderRepository.save(newOrder);
    }

    public List<Order> listAllOrders()
    {
        return orderRepository.findAll();
    }
}
