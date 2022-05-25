package com.example.foodpanda.business;

import com.example.foodpanda.model.Food;

import java.util.ArrayList;
import java.util.List;

public class CartService
{
    private List<Food> foodsInCart;
    private Double totalPriceInCart;

    public CartService()
    {
        foodsInCart = new ArrayList<>();
        totalPriceInCart = 0.0;
    }

    public void addFoodToCart(Food food)
    {
        foodsInCart.add(food);
        totalPriceInCart = totalPriceInCart + food.getPrice();
    }

    public List<Food> getFoodsInCart()
    {
        return foodsInCart;
    }

    public Double getTotalPriceInCart()
    {
        return totalPriceInCart;
    }
}
