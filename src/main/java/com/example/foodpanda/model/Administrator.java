package com.example.foodpanda.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "admins")
public class Administrator
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String email;

    @Column
    private String restaurantName;

    @Column
    private String restaurantPhoneNumber;

    @Column
    private String location;

    @Column
    private String availableDeliveryZones;

    @OneToMany
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id")
    private List<Food> menu;

    @OneToMany(mappedBy = "id")
    private List<Order> orders;


    public Administrator(){}

    public Administrator(String username, String password, String email, String restaurantName, String restaurantPhoneNumber, String location, String availableDeliveryZones, List<Food> menu, List<Order> orders)
    {
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

    public Integer getId()
    {
        return id;
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
