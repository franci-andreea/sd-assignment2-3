package com.example.foodpanda.controller;

import com.example.foodpanda.business.UserService;
import com.example.foodpanda.business.dto.LogInDTO;
import com.example.foodpanda.model.authentication.JWTUserResponse;
import com.example.foodpanda.security.UserDetailsImplementation;
import com.example.foodpanda.security.jwt.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class AuthenticationController
{
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    JWTUtils jwtUtils;

    @PostMapping("/authUser")
    @ResponseBody
    public ResponseEntity<?> authenticateUser(@RequestBody LogInDTO loginDTO)
    {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJWTToken(authentication);

        try
        {
            UserDetailsImplementation userDetails = (UserDetailsImplementation) authentication.getPrincipal();
            JWTUserResponse jwtUserResponse = new JWTUserResponse(jwt,
                                                                  userDetails.getId(),
                                                                  userDetails.getFirstName(),
                                                                  userDetails.getLastName(),
                                                                  userDetails.getEmail(),
                                                                  userDetails.getPhoneNumber(),
                                                                  userDetails.getAddress(),
                                                                  userDetails.getUsername(),
                                                                  userDetails.getPassword());
            return ResponseEntity.ok().body(jwtUserResponse);
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Wrong username or password");
        }
    }



}
