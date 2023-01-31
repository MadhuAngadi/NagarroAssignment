package com.nagarro.assignment.controllers;
import com.nagarro.assignment.services.StatementService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
public class StatementServiceTestConfiguration {
    @Bean
    @Primary
    public StatementService statementService() {
        return Mockito.mock(StatementService.class);
    }
}

