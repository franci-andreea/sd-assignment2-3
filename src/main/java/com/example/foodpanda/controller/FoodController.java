package com.example.foodpanda.controller;

import com.example.foodpanda.business.FoodService;
import com.example.foodpanda.business.dto.NewFoodDTO;
import com.example.foodpanda.model.Administrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FoodController
{
    final FoodService foodService;

    /**
     * constructor for food controller class
     * @param foodService
     */
    @Autowired
    public FoodController(FoodService foodService)
    {
        this.foodService = foodService;
    }

    /**
     * POST REQUEST FOR ADDING FOOD IN A MENU AND TO THE DATABASE
     * @param newFood - the food to be added in the database
     * @return
     */
    @PostMapping("admin/addFood")
    public ResponseEntity addFoodToMenu(@RequestBody NewFoodDTO newFood)
    {
        System.out.println("FOOD CONTROLLER - newFood.restaurantName = " + newFood.getRestaurantName());
        return ResponseEntity.ok().body(foodService.addFoodToMenu(newFood));
    }

}
