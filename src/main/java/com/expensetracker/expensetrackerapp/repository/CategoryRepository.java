package com.expensetracker.expensetrackerapp.repository;

import com.expensetracker.expensetrackerapp.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
