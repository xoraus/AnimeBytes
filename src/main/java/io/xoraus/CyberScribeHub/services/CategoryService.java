package io.xoraus.CyberScribeHub.services;

import io.xoraus.CyberScribeHub.payloads.CategoryDto;

import java.util.List;

public interface CategoryService {
    // create
    CategoryDto createCategory(CategoryDto categoryDto);

    // get
    CategoryDto getCategory(Integer categoryId);

    // update
    CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);

    // delete
    void deleteCategory(Integer categoryId);

    // get All
    List<CategoryDto> getCategories();
}
