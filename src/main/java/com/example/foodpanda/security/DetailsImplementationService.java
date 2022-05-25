package com.example.foodpanda.security;

import com.example.foodpanda.model.Administrator;
import com.example.foodpanda.model.User;
import com.example.foodpanda.repository.AdministratorRepository;
import com.example.foodpanda.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DetailsImplementationService implements UserDetailsService
{
    private static final Logger logger = LogManager.getLogger(DetailsImplementationService.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    AdministratorRepository administratorRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User user = null;
        Administrator administrator = null;

        try
        {
            user = userRepository.findByUsername(username).get();
            logger.info("User " + username + " has been found in the database");
            return new UserDetailsImplementation(user);
        }
        catch (Exception e)
        {
            try
            {
                administrator = administratorRepository.findByUsername(username).get();
                logger.info("Admin with username " + username + " has been found in the database");
                return new AdministratorDetailsImplementation(administrator);
            }
            catch (Exception exception)
            {
                String errorMessage = "User with username " + username + " NOT FOUND";
                exception.printStackTrace();
                logger.error(errorMessage);
                throw new UsernameNotFoundException(errorMessage);
            }
        }
    }
}
