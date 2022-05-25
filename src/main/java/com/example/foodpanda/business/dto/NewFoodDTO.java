package com.example.foodpanda.business.dto;

import com.example.foodpanda.model.FoodCategory;

public class NewFoodDTO
{
    private String name;
    private String description;
    private Double price;
    private FoodCategory category;
    private String restaurantName;

    public NewFoodDTO(String name, String description, Double price, FoodCategory category, String restaurantName)
    {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.restaurantName = restaurantName;
    }

    public String getName()
    {
        return name;
    }

    public String getDescription()
    {
        return description;
    }

    public Double getPrice()
    {
        return price;
    }

    public FoodCategory getCategory()
    {
        return category;
    }

    public String getRestaurantName()
    {
        return restaurantName;
    }
}
