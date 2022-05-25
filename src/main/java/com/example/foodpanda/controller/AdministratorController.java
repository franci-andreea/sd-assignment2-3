package com.example.foodpanda.controller;

import com.example.foodpanda.business.AdministratorService;
import com.example.foodpanda.business.dto.LogInDTO;
import com.example.foodpanda.model.Administrator;
import com.example.foodpanda.model.FoodCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

@RestController
public class AdministratorController
{
    final AdministratorService administratorService;

    /**
     * constructor for administrator controller
     * @param administratorService
     */
    @Autowired
    public AdministratorController(AdministratorService administratorService)
    {
        this.administratorService = administratorService;
    }

    /**
     * POST REQUEST FOR LOGIN AN ADMIN
     * @param (Path Variable) username - the username introduced by the admin
     * @param (Path Variable) password - the password introduced by the admin
     */
    @PostMapping("admin/{username}/{password}")
    public ResponseEntity loginAdmin(@PathVariable String username, @PathVariable String password)
    {
        try
        {
            LogInDTO loginCredentials = new LogInDTO(username, password);

            return ResponseEntity.ok().body(administratorService.returnAdminAfterLogin(loginCredentials));

        } catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.toString());
        }
    }

    /**
     * POST REQUEST FOR REGISTER A NEW ADMINISTRATOR (RESTAURANT)
     * @param newAdmin - an Administrator object containing all the details introduced on the register page
     * @return
     */
    @PostMapping("/register-admin")
    public ResponseEntity registerAdmin(@RequestBody Administrator newAdmin)
    {
        try
        {
            administratorService.registerAdministrator(newAdmin);
        } catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
            return ResponseEntity.ok().body("ADMIN CONTROLLER / REGISTER ADMIN - EXCEPTION CAUGHT");
        }

        return ResponseEntity.ok().body("ADMIN CONTROLLER / REGISTER ADMIN - SUCCESSFUL ADMIN REGISTRATION");
    }

    /**
     * GET REQUEST FOR OBTAINING THE FOOD CATEGORIES
     * @return
     */
    @GetMapping("admin/getCategories")
    public ResponseEntity getFoodCategories()
    {
        List<FoodCategory> categories = Arrays.asList(FoodCategory.values());

        //System.out.println("ADMIN CONTROLLER - CATEGORIES SIZE = " + categories.size());

        return ResponseEntity.ok().body(categories);
    }

    /**
     * GET REQUEST FOR OBTAINING THE CURRENT RESTAURANT SELECTED BY THE USER
     * @param (Path variable) id - the id of the current restaurant selected in the user homepage page
     * @return
     */
    @GetMapping("/getCurrentRestaurant/{id}")
    public ResponseEntity getRestaurant(@PathVariable Integer id)
    {
        return ResponseEntity.ok().body(administratorService.findRestaurantById(id));
    }

    /**
     * GET REQUEST FOR OBTAINING A SPECIFIC LIST OF RESTAURANT AFTER A SEARCH OPERATION MADE BY THE USER
     * @param (Path Variable) searchString - the sequence of characters introduced by the user in the search bar
     * @return
     */
    @GetMapping("/getRestaurants/{searchString}")
    public ResponseEntity getAllRestaurants(@PathVariable String searchString)
    {
        System.out.println("restaurant name from search bar: " + searchString);
        if(searchString.equals("all"))
            return ResponseEntity.ok().body(administratorService.getAllRestaurants());
        else
            return ResponseEntity.ok().body(administratorService.searchRestaurants(searchString));
    }

    /**
     * GET REQUEST FOR OBTAINING THE RESTAURANT'S MENU
     * @param currentRestaurant - the restaurant for which we want to get the menu from the database
     * @return
     */
    @GetMapping("/admin/getMenu")
    public ResponseEntity getMenu(@RequestBody Administrator currentRestaurant)
    {
        return ResponseEntity.ok().body(administratorService.getMenu(currentRestaurant));
    }

}
