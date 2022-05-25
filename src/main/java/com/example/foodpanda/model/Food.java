package com.example.foodpanda.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "foods")
public class Food
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private Double price;

    @Column
    private FoodCategory category;

    /*
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "restaurant_id")
    private Administrator restaurant;
    */

    public Food()
    {

    }

    public Food(String name, String description, Double price, FoodCategory category)
    {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
    }

    public String getName()
    {
        return name;
    }

    public String getDescription()
    {
        return description;
    }

    public Double getPrice()
    {
        return price;
    }

    public FoodCategory getCategory()
    {
        return category;
    }

    @Override
    public String toString()
    {
        String stringBuilder = name + "\t\t\t\t\t" + price + "\n" +
                description + "\n";

        return stringBuilder;
    }
}
