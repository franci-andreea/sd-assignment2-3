package com.example.foodpanda.model.authentication;

import com.example.foodpanda.model.Food;
import com.example.foodpanda.model.Order;

import java.util.List;

public class JWTAdministratorResponse
{
    private String jwt;
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String restaurantName;
    private String restaurantPhoneNumber;
    private String location;
    private String availableDeliveryZones;
    private List<Food> menu;
    private List<Order> orders;

    public JWTAdministratorResponse(String jwt, Integer id, String username, String password, String email, String restaurantName, String restaurantPhoneNumber, String location, String availableDeliveryZones, List<Food> menu, List<Order> orders)
    {
        this.jwt = jwt;
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.restaurantName = restaurantName;
        this.restaurantPhoneNumber = restaurantPhoneNumber;
        this.location = location;
        this.availableDeliveryZones = availableDeliveryZones;
        this.menu = menu;
        this.orders = orders;
    }

    public String getJwt()
    {
        return jwt;
    }

    public void setJwt(String jwt)
    {
        this.jwt = jwt;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getRestaurantName()
    {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName)
    {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantPhoneNumber()
    {
        return restaurantPhoneNumber;
    }

    public void setRestaurantPhoneNumber(String restaurantPhoneNumber)
    {
        this.restaurantPhoneNumber = restaurantPhoneNumber;
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public String getAvailableDeliveryZones()
    {
        return availableDeliveryZones;
    }

    public void setAvailableDeliveryZones(String availableDeliveryZones)
    {
        this.availableDeliveryZones = availableDeliveryZones;
    }

    public List<Food> getMenu()
    {
        return menu;
    }

    public void setMenu(List<Food> menu)
    {
        this.menu = menu;
    }

    public List<Order> getOrders()
    {
        return orders;
    }

    public void setOrders(List<Order> orders)
    {
        this.orders = orders;
    }
}
