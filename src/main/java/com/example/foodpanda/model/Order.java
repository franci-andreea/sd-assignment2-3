package com.example.foodpanda.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToMany
    @JoinTable(
            name = "foods_order",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "food_id")
    )
    private List<Food> foodsOrdered;

    @Column
    private Double totalPrice;

    @Column
    private OrderStatus status;

    public Order() {}

    public Order(List<Food> foodsOrdered, Double totalPrice, OrderStatus status)
    {
        this.foodsOrdered = foodsOrdered;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    public List<Food> getFoodsOrdered()
    {
        return foodsOrdered;
    }

    public Double getTotalPrice()
    {
        return totalPrice;
    }

    public OrderStatus getStatus()
    {
        return status;
    }
}
