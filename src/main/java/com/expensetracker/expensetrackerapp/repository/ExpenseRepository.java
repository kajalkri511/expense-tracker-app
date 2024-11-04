package com.expensetracker.expensetrackerapp.repository;

import com.expensetracker.expensetrackerapp.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense,Long> {
}
