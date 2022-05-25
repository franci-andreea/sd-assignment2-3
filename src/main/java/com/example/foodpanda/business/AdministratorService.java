package com.example.foodpanda.business;

import com.example.foodpanda.business.dto.LogInDTO;
import com.example.foodpanda.business.validators.RegisterValidator;
import com.example.foodpanda.model.Administrator;

import com.example.foodpanda.model.Food;
import com.example.foodpanda.repository.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdministratorService
{
    private final AdministratorRepository administratorRepository;

    /**
     * constructor for administrator service
     * @param administratorRepository - repository that simulates the admins table from the database
     */
    @Autowired
    public AdministratorService(AdministratorRepository administratorRepository)
    {
        this.administratorRepository = administratorRepository;
    }

    /**
     * register a new administrator
     * @param newAdministrator - represents a new administrator account that contains all the credentials required
     * @return true if registration was successful, false otherwise
     * @throws NoSuchAlgorithmException
     */
    public boolean registerAdministrator(Administrator newAdministrator) throws NoSuchAlgorithmException
    {
        RegisterValidator validator = RegisterValidator.getInstance();

        System.out.println(newAdministrator.toString());

        if((validator.checkUsername(newAdministrator.getUsername()) && validator.checkEmail(newAdministrator.getEmail()))
        && validator.checkPhoneNumber(newAdministrator.getRestaurantPhoneNumber()))
        {
            //check for uniqueness
            if(!administratorRepository.findByUsername(newAdministrator.getUsername()).isPresent())
            {
                //no administrator found with this username
                newAdministrator.setPassword(RegisterService.encodePassword(newAdministrator.getPassword()));
                newAdministrator.setMenu(new ArrayList<>());
                administratorRepository.save(newAdministrator);

                return true;
            }
            else
            {
                System.out.println("ADMIN SERVICE - Can't insert administrator in the database because there already exists one with this username");
                return false;
            }
        }

        System.out.println("ADMIN SERVICE - Can't insert administrator in the database because validations are not respected");
        return false;
    }

    /**
     * log in an existing administrator
     * @param admin contains admin credentials for log in (username and password)
     * @return true if log in credentials are correct, false otherwise
     * @throws NoSuchAlgorithmException
     */
    public boolean checkLoginAdmin(LogInDTO admin) throws NoSuchAlgorithmException
    {
        String encodedPassword = RegisterService.encodePassword(admin.getPassword());

        if(administratorRepository.existsAdministratorByUsernameAndPassword(admin.getUsername(), encodedPassword))
        {
            //found login credentials
            return true;
        }

        System.out.println("ADMIN SERVICE - no user was found having username: " + admin.getUsername());
        return false;
    }

    /**
     * method used to get the admin details after log in
     * @param admin contains the administrator's details (besides the log in credentials)
     * @return the administrator in case it was found, null otherwise
     * @throws NoSuchAlgorithmException
     */
    public Optional<Administrator> returnAdminAfterLogin(LogInDTO admin) throws NoSuchAlgorithmException
    {
        if(checkLoginAdmin(admin))
        {
            Optional<Administrator> administrator = administratorRepository.findByUsername(admin.getUsername());

            return administrator;
        }

        return null;
    }

    /**
     * get all the restaurants from the database
     * @return the list of restaurants that are in the database
     */
    public List<Administrator> getAllRestaurants()
    {
        return administratorRepository.findAll();
    }

    /**
     * method to find restaurants' names based on a keyword
     * @param word represents the keyword in the name
     * @return a list containing all restaurants that have in their name that keyword
     */
    public List<Administrator> searchRestaurants(String word)
    {
        List<Administrator> foundRestaurants = new ArrayList<>();

        if(word.isEmpty())
        {
            return administratorRepository.findAll();
        }

        for(Administrator restaurant : getAllRestaurants())
        {
            if(restaurant.getRestaurantName().contains(word))
            {
                foundRestaurants.add(restaurant);
            }
        }

        if(foundRestaurants.isEmpty())
        {
            System.out.println("ADMINISTRATOR SERVICE - NO RESTAURANT HAS THIS WORD IN ITS NAME");
            return null;
        }

        return foundRestaurants;
    }

    /**
     * method to find a restaurant (admin) by id in the database
     * @param id
     * @return
     */
    public Optional<Administrator> findRestaurantById(Integer id)
    {
        return administratorRepository.findById(id);
    }

    /**
     * method to find a restaurant (admin) by name in the database
     * @param restaurantName
     * @return - an Administrator object in case the name is found in the database
     *         - null otherwise
     */
    public Optional<Administrator> findRestaurantByName(String restaurantName)
    {
        return administratorRepository.findByRestaurantName(restaurantName);
    }

    /**
     * method to update the restaurant's menu (list of foods)
     * @param restaurantToUpdate - the restaurant for which we have to update the menu
     * @return the same restaurant object with the updated menu list
     */
    public Administrator updateRestaurantMenu(Administrator restaurantToUpdate)
    {
        return administratorRepository.save(restaurantToUpdate);
    }

    /**
     * useful getter to obtain a restaurant's menu
     * @param currentRestaurant - the restaurant for which we want to get the menu
     * @return the menu (list of foods) for the current restaurant
     */
    public List<Food> getMenu(Administrator currentRestaurant)
    {
        return currentRestaurant.getMenu();
    }

}
