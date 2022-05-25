package com.example.foodpanda.business;

import com.example.foodpanda.business.dto.LogInDTO;
import com.example.foodpanda.business.validators.RegisterValidator;
import com.example.foodpanda.model.Administrator;
import com.example.foodpanda.model.Food;
import com.example.foodpanda.model.User;
import com.example.foodpanda.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService
{
    private final UserRepository userRepository;
    private final AdministratorService administratorService;
    //private final PasswordEncoder passwordEncoder;

    /**
     * constructor for the user service class
     * @param userRepository
     * @param administratorService
     */
    @Autowired
    public UserService(UserRepository userRepository, AdministratorService administratorService)
    {
        this.userRepository = userRepository;
        this.administratorService = administratorService;
    }

    /**
     * method to register a new user (client) and add it to the database
     * @param newUser - the new user that has to be added to the database
     * @throws NoSuchAlgorithmException
     */
    public void registerUser(User newUser) throws NoSuchAlgorithmException
    {
        RegisterValidator validator = RegisterValidator.getInstance();

        System.out.println(newUser.toString());

        if((validator.checkName(newUser.getFirstName()) && validator.checkName(newUser.getLastName())) &&
                (validator.checkEmail(newUser.getEmail()) && validator.checkPhoneNumber(newUser.getPhoneNumber())) &&
                (validator.checkAddress(newUser.getAddress()) && validator.checkUsername(newUser.getUsername())))
        {
            //check for uniqueness
            if(!userRepository.findByUsername(newUser.getUsername()).isPresent())
            {
                //no user with this username, so it is safe to register him and add its data in the database
                newUser.setPassword(RegisterService.encodePassword(newUser.getPassword()));
                newUser.setOrders(new ArrayList<>());
                userRepository.save(newUser);
            }
            else
            {
                System.out.println("USER SERVICE - Can't insert user in the database because there is already a user with this username");
            }
        }
        else
        {
            System.out.println("USER SERVICE - Can't insert user in the database because validations are not respected");
        }
    }

    /**
     * method to check if the login credentials are in the databse
     * @param user - the login credentials introduced by the user
     * @return - true if we have found the credentials in the database (if the user exists)
     * @throws NoSuchAlgorithmException
     */
    public boolean checkLoginUser(LogInDTO user) throws NoSuchAlgorithmException
    {
        String encodedPassword = RegisterService.encodePassword(user.getPassword());

        if(userRepository.existsUserByUsernameAndPassword(user.getUsername(), encodedPassword))
        {
            //found login credentials
            return true;
        }

        System.out.println("USER SERVICE - no user was found having username: " + user.getUsername());
        return false;
    }

    /**
     * method to obtain user's details for the frontend after the login operation
     * @param user - the user's login credentials introduced for the login operation
     * @return - a user object that contains all the details about the current user that is logged in
     *         - null, if the user is not found in the database based on the login credentials
     * @throws NoSuchAlgorithmException
     */
    public Optional<User> returnUserAfterLogin(LogInDTO user) throws NoSuchAlgorithmException
    {
        if(checkLoginUser(user))
        {
            Optional<User> maybeUser = userRepository.findByUsername(user.getUsername());

            return maybeUser;
        }

        return null;
    }

    /**
     * method to get a list containing all the restaurants that are in the database
     * @return the list of restaurants
     */
    public List<Administrator> viewRestaurants()
    {
        return administratorService.getAllRestaurants();
    }

    /**
     * method to get a user from the database based on its username
     * @param username - the username by which we look through the database
     * @return - a user from the database having that username
     *         - null, if there is no user with such username in the database
     */
    public Optional<User> getUserByUsername(String username)
    {
        return userRepository.findByUsername(username);
    }

}
