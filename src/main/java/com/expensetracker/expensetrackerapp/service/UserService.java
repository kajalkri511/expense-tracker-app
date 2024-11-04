package com.expensetracker.expensetrackerapp.service;

import com.expensetracker.expensetrackerapp.entity.User;

public interface UserService {
    Long getUserIdByUsername(String username);
}