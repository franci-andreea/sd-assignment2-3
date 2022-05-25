package com.example.foodpanda.security;

import com.example.foodpanda.model.Administrator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

public class AdministratorDetailsImplementation implements UserDetails
{
    private Administrator administrator;

    public AdministratorDetailsImplementation(Administrator administrator)
    {
        this.administrator = administrator;
    }

    public Integer getId()
    {
        return administrator.getId();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    @Override
    public String getPassword()
    {
        return administrator.getPassword();
    }

    @Override
    public String getUsername()
    {
        return administrator.getUsername();
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

        AdministratorDetailsImplementation admin = (AdministratorDetailsImplementation) object;

        return this.administrator.getId() == admin.getId();
    }
}
