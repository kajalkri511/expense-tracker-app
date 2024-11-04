package com.expensetracker.expensetrackerapp.mapper;

import com.expensetracker.expensetrackerapp.dto.CategoryDto;
import com.expensetracker.expensetrackerapp.dto.ExpenseDto;
import com.expensetracker.expensetrackerapp.entity.Category;
import com.expensetracker.expensetrackerapp.entity.Expense;
import com.expensetracker.expensetrackerapp.entity.User;
import com.expensetracker.expensetrackerapp.repository.UserRepository;

public class ExpenseMapper {

    private UserRepository userRepository; // Add UserRepository to fetch user

    public static Expense mapToExpense(ExpenseDto expenseDto, User user) {
        Category category = new Category();
        category.setId(expenseDto.categoryDto().id());
        return new Expense(
                expenseDto.id(),
                expenseDto.amount(),
                expenseDto.expenseDate(),
                category,
                user // include the user here
        );
    }

    public static ExpenseDto mapToExpenseDto(Expense expense){
        return new ExpenseDto(
                expense.getId(),
                expense.getAmount(),
                expense.getExpenseDate(),
                new CategoryDto(
                        expense.getCategory().getId(),
                        expense.getCategory().getName()
                )
        );
    }
}
