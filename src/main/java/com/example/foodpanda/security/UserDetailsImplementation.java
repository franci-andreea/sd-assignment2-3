package com.example.foodpanda.security;

import com.example.foodpanda.model.Order;
import com.example.foodpanda.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class UserDetailsImplementation implements UserDetails
{
    private User user;

    public UserDetailsImplementation(User user)
    {
        this.user = user;
    }

    public Integer getId()
    {
        return user.getId();
    }

    public String getFirstName()
    {
        return user.getFirstName();
    }

    public String getLastName()
    {
        return user.getLastName();
    }

    public String getEmail()
    {
        return user.getEmail();
    }

    public String getPhoneNumber()
    {
        return user.getPhoneNumber();
    }

    public String getAddress()
    {
        return user.getAddress();
    }

    public List<Order> getOrders()
    {
        return user.getOrders();
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword()
    {
        return user.getPassword();
    }

    @Override
    public String getUsername()
    {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return true;
    }

    @Override
    public boolean equals(Object object)
    {
        if(this == object)
            return true;

        if(object == null || getClass() != object.getClass())
            return false;

        UserDetailsImplementation user = (UserDetailsImplementation) object;

        return this.user.getId() == user.getId();
    }
}
