package com.example.foodpanda.business.dto;

public class LogInDTO
{
    private String username;
    private String password;

    public LogInDTO(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }
}
