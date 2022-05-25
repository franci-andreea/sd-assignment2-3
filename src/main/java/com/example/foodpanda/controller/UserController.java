package com.example.foodpanda.controller;

import com.example.foodpanda.business.UserService;
import com.example.foodpanda.business.dto.LogInDTO;
import com.example.foodpanda.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
public class UserController
{
    final UserService userService;

    /**
     * constructor for the user controller class
     * @param userService - user service that takes care of the logic that involves the user's details and the users table
     *                      from the database
     */
    @Autowired
    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    /**
     * method to login a user (normal user)
     * @param username - username introduced by the user
     * @param password - password introduced by the user
     * @return
     */
    @PostMapping("user/{username}/{password}")
    public ResponseEntity loginUser(@PathVariable String username, @PathVariable String password)
    {
       try
       {
           LogInDTO loginCredentials = new LogInDTO(username, password);

           return ResponseEntity.ok().body(userService.returnUserAfterLogin(loginCredentials));

       } catch (NoSuchAlgorithmException e)
       {
           e.printStackTrace();
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.toString());
       }
    }

    /**
     * POST REQUEST FOR REGISTER A NEW USER
     * @param newUser - a User object containing all the details introduced in the register page
     *                - the new user to be added into the database
     * @return
     */
    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody User newUser)
    {
        try
        {
            userService.registerUser(newUser);
        } catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
            return ResponseEntity.ok().body("USER CONTROLLER / REGISTER USER - EXCEPTION CAUGHT");
        }

        return ResponseEntity.ok().body("USER CONTROLLER / REGISTER USER - SUCCESSFUL USER REGISTRATION");
    }

    /**
     * GET REQUEST TO OBTAIN THE LIST OF RESTAURANTS FOR THE USER PAGE
     * @return
     */
    @GetMapping("/user/listRestaurants")
    public ResponseEntity viewAllRestaurants()
    {
        return ResponseEntity.ok().body(userService.viewRestaurants());
    }
}
