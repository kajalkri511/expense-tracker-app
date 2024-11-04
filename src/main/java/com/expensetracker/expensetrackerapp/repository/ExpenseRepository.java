package com.expensetracker.expensetrackerapp.repository;

import com.expensetracker.expensetrackerapp.entity.Expense;
import com.expensetracker.expensetrackerapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense,Long> {
    List<Expense> findByUser(User user);

    List<Expense> findAllByUserId(Long userId);

    List<Expense> findAllByUser_Id(Long id);
}
