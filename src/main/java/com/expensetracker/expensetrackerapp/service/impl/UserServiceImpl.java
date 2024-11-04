package com.expensetracker.expensetrackerapp.service.impl;

import com.expensetracker.expensetrackerapp.entity.User;
import com.expensetracker.expensetrackerapp.exception.ResourceNotFoundException;
import com.expensetracker.expensetrackerapp.repository.UserRepository;
import com.expensetracker.expensetrackerapp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Long getUserIdByUsername(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            return userOptional.get().getId();
        } else {
            throw new RuntimeException("User not found with username: " + username);
        }
    }
}

