package com.example.foodpanda.business;

import com.example.foodpanda.business.dto.NewFoodDTO;
import com.example.foodpanda.business.validators.RegisterValidator;
import com.example.foodpanda.model.Administrator;
import com.example.foodpanda.model.Food;
import com.example.foodpanda.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FoodService
{
    private final AdministratorService administratorService;
    private final FoodRepository foodRepository;

    /**
     * constructor for food service
     * @param foodRepository - repository that simulates the foods table from the database
     * @param administratorService - administrator service which helps with some logical business
     *                               from the administrator's side
     */
    @Autowired
    public FoodService(FoodRepository foodRepository, AdministratorService administratorService)
    {
        this.foodRepository = foodRepository;
        this.administratorService = administratorService;
    }

    /**
     * method to add food to a restaurant's menu
     * @param newFood - the food that is to be added to the menu
     * @return - true, if the operation was successful
     *         - false, otherwise
     */
    public boolean addFoodToMenu(NewFoodDTO newFood)
    {
        RegisterValidator validator = RegisterValidator.getInstance();
        if(validator.checkName(newFood.getName()))
        {
            //valid name food, it's okay to add in
            Food food = new Food(newFood.getName(), newFood.getDescription(), newFood.getPrice(), newFood.getCategory());
            food = foodRepository.save(food);

            Optional<Administrator> maybeRestaurant = administratorService.findRestaurantByName(newFood.getRestaurantName());
            if(maybeRestaurant.isPresent())
            {
                Administrator restaurant = maybeRestaurant.get();
                maybeRestaurant.get().getMenu().add(food);
                restaurant = administratorService.updateRestaurantMenu(maybeRestaurant.get());

                System.out.println("FOOD SERVICE - RESTAURANT MENU UPDATED");

                System.out.println("Current menu for restaurant " + restaurant.getRestaurantName() + ": ");
                for(Food currentFood : restaurant.getMenu())
                {
                    System.out.print(currentFood.getName() + ", ");
                }
            }
            else
                System.out.println("FOOD SERVICE - NO RESTAURANT FOUND, SO NO LINKING IS CREATED BETWEEN FOOD AND RESTAURANT");

            System.out.println("FOOD SERVICE - FOOD ADDED IN THE DATABASE");

            return true;
        }

        System.out.println("FOOD SERVICE - can't insert food in the database because food with name " + " already exists");
        return false;
    }
}
