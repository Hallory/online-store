package org.electronicsstore.backend.services;

import lombok.RequiredArgsConstructor;
import org.electronicsstore.backend.dtos.CategoryDto;
import org.electronicsstore.backend.exceptions.CategoryNotFoundException;
import org.electronicsstore.backend.exceptions.ProductAlreadyExistsException;
import org.electronicsstore.backend.model.Category;
import org.electronicsstore.backend.repos.CategoryRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryService {
    private final CategoryRepo categoryRepo;

    public Category findByCategoryName(String name) {
        return categoryRepo.findByName(name).orElseThrow(() -> new CategoryNotFoundException(name));
    }

    public List<CategoryDto> findAll() {
        return categoryRepo.findAll().stream().map(CategoryDto::categoryToCategoryDto).toList();
    }

    public CategoryDto findById(String categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException(categoryId));
        return CategoryDto.categoryToCategoryDto(category);
    }

    public CategoryDto saveOne(CategoryDto categoryDto) {
        if (categoryRepo.existsByName(categoryDto.name())) {
            throw new ProductAlreadyExistsException("Product " + categoryDto.name() + " is present");
        }
        LocalDateTime timeNow = LocalDateTime.now();

        Category category = new Category();
        category.setName(categoryDto.name());
        category.setCreatedAt(timeNow);

        return CategoryDto.categoryToCategoryDto(categoryRepo.save(category));
    }

    public void updateOne(String categoryId, CategoryDto categoryDto) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException(categoryId));;
        category.setName(categoryDto.name());

        categoryRepo.save(category);
    }

    public void deleteOne(String categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException(categoryId));;
        categoryRepo.delete(category);
    }
}
