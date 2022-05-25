package com.example.foodpanda.security.jwt;

import com.example.foodpanda.security.AdministratorDetailsImplementation;
import com.example.foodpanda.security.UserDetailsImplementation;
import io.jsonwebtoken.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

@Component
public class JWTUtils implements Serializable
{
    private static final Logger logger = LogManager.getLogger(JWTUtils.class);

//    @Value("${jwtSecret}")
    private String jwtSecret;

//    @Value("${jwtExpirationMs}")
    private int jwtExpirationMs;

    public String generateJWTToken(Authentication authentication)
    {
        try
        {
            UserDetailsImplementation userPrincipal = (UserDetailsImplementation) authentication.getPrincipal();
            return Jwts.builder()
                    .setSubject((userPrincipal.getUsername()))
                    .setIssuedAt(new Date())
                    .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                    .signWith(SignatureAlgorithm.HS512, jwtSecret)
                    .compact();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            AdministratorDetailsImplementation adminPrincipal = (AdministratorDetailsImplementation) authentication.getPrincipal();
            return Jwts.builder()
                    .setSubject((adminPrincipal.getUsername()))
                    .setIssuedAt(new Date())
                    .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                    .signWith(SignatureAlgorithm.HS512, jwtSecret)
                    .compact();
        }
    }

    public String getUsernameFromJwtToken(String token)
    {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken)
    {
        try
        {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        }
        catch (SignatureException e)
        {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        }
        catch (MalformedJwtException e)
        {
            logger.error("Invalid JWT token: {}", e.getMessage());
        }
        catch (ExpiredJwtException e)
        {
            logger.error("JWT token is expired: {}", e.getMessage());
        }
        catch (UnsupportedJwtException e)
        {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        }
        catch (IllegalArgumentException e)
        {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }
}
