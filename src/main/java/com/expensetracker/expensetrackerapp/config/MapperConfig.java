package com.expensetracker.expensetrackerapp.config;
import com.expensetracker.expensetrackerapp.mapper.ExpenseMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
    @Bean
    public ExpenseMapper expenseMapper() {
        return new ExpenseMapper();
    }
}