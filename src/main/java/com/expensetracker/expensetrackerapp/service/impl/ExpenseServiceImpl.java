package com.expensetracker.expensetrackerapp.service.impl;

import com.expensetracker.expensetrackerapp.dto.CategoryDto;
import com.expensetracker.expensetrackerapp.dto.ExpenseDto;
import com.expensetracker.expensetrackerapp.entity.Category;
import com.expensetracker.expensetrackerapp.entity.Expense;
import com.expensetracker.expensetrackerapp.entity.User;
import com.expensetracker.expensetrackerapp.exception.ResourceNotFoundException;
import com.expensetracker.expensetrackerapp.mapper.CategoryMapper;
import com.expensetracker.expensetrackerapp.mapper.ExpenseMapper;
import com.expensetracker.expensetrackerapp.repository.CategoryRepository;
import com.expensetracker.expensetrackerapp.repository.ExpenseRepository;
import com.expensetracker.expensetrackerapp.repository.UserRepository;
import com.expensetracker.expensetrackerapp.service.ExpenseService;
import com.expensetracker.expensetrackerapp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ExpenseServiceImpl implements ExpenseService {

    private ExpenseRepository expenseRepository;
    private CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final ExpenseMapper expenseMapper;

//    @Override
//    public ExpenseDto createExpense(ExpenseDto expenseDto) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String username = (String) authentication.getPrincipal();
//        User user = userService.getUserByUsername(username); // Assuming you have this method
//
//        Expense expense = ExpenseMapper.mapToExpense(expenseDto, user); // Pass user to mapper
//
//        Expense savedExpense = expenseRepository.save(expense);
//        return ExpenseMapper.mapToExpenseDto(savedExpense);
//    }

    @Override
    public ExpenseDto createExpense(ExpenseDto expenseDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = ((UserDetails) authentication.getPrincipal()).getUsername();

        // Assuming you have a userService method to get the user by username
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with username: " + username));

        Expense expense = ExpenseMapper.mapToExpense(expenseDto, user);
        Expense savedExpense = expenseRepository.save(expense);
        return ExpenseMapper.mapToExpenseDto(savedExpense);
    }

    @Override
    public ExpenseDto getExpenseById(Long expenseId) {
        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new ResourceNotFoundException("expense not found with id: "+expenseId));
        return ExpenseMapper.mapToExpenseDto(expense);
    }

//    @Override
//    public List<ExpenseDto> getAllExpenses() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String username = (String) authentication.getPrincipal();
//        User user = userService.getUserByUsername(username); // Fetch user by username
//
//        List<Expense> expenses = expenseRepository.findByUser(user); // Fetch expenses by user
//        return expenses.stream()
//                .map(ExpenseMapper::mapToExpenseDto)
//                .collect(Collectors.toList());
//
////        List<Expense> expenses = expenseRepository.findAll();
////        return expenses.stream()
////                .map((expense) -> ExpenseMapper.mapToExpenseDto(expense))
////                .collect(Collectors.toList());
//    }
    @Override
    public List<ExpenseDto> getAllExpenses() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = ((UserDetails) authentication.getPrincipal()).getUsername();

        // Retrieve the user from the database
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with username: " + username));

        // Fetch all expenses for the user
        List<Expense> expenses = expenseRepository.findAllByUser_Id(user.getId());

        // Map expenses to ExpenseDto
        return expenses.stream()
                .map(ExpenseMapper::mapToExpenseDto)
                .collect(Collectors.toList());
    }

    @Override
    public ExpenseDto updateExpense(Long expenseId, ExpenseDto expenseDto) {
        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new ResourceNotFoundException("expense not found with id: "+expenseId));
        expense.setAmount(expenseDto.amount());
        expense.setExpenseDate(expenseDto.expenseDate());
        if(expenseDto.categoryDto() !=null){
            Category category = categoryRepository.findById(expenseDto.categoryDto().id())
                    .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: "+expenseDto.categoryDto().id()));
            expense.setCategory(category);
        }
        Expense updatedExpense = expenseRepository.save(expense);
        return ExpenseMapper.mapToExpenseDto(updatedExpense);
    }

    @Override
    public void deleteExpense(Long expenseId) {
        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new ResourceNotFoundException("expense not found with id: "+expenseId));
        expenseRepository.delete(expense);
    }
}
