package com.expensetracker.expensetrackerapp.service;

import com.expensetracker.expensetrackerapp.dto.LoginDto;
import com.expensetracker.expensetrackerapp.dto.RegisterDto;

public interface AuthService {
    String register(RegisterDto registerDto);
    String login(LoginDto loginDto);
}
