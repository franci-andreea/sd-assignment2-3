package com.example.foodpanda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class FoodPandaApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(FoodPandaApplication.class, args);
    }

//    @Bean
//    public WebMvcConfigurer corsConfigure(){
//        return new WebMvcConfigurer(){
//            @Override
//            public void addCorsMappings(CorsRegistry registry){
//                registry.addMapping("/**").allowedMethods("GET","POST","PUT","DELETE")
//                        .allowedOrigins("*")
//                        .allowedHeaders("*");
//            }
//        };
//    }
}
