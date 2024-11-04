package com.expensetracker.expensetrackerapp.mapper;

import com.expensetracker.expensetrackerapp.dto.CategoryDto;
import com.expensetracker.expensetrackerapp.entity.Category;

public class CategoryMapper {
    public static Category mapToCategory(CategoryDto categoryDto){
        return new Category(
                categoryDto.id(),
                categoryDto.name()
        );
    }

    public static CategoryDto mapToCategoryDto(Category category){
        return new CategoryDto(
                category.getId(),
                category.getName()
        );
    }
}
