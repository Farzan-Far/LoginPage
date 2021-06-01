package com.example.demo3;

import com.example.demo3.Domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Beans
{
    @Bean
    public User getUser()
    {
        return new User();
    }

}
