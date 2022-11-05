package com.Jyoti.blog.BlogappApi.Services;

import com.Jyoti.blog.BlogappApi.Payloads.CategoryDto;

import java.util.List;

public interface CategoryService {

    //create
    public CategoryDto createCategory(CategoryDto categoryDto);

    //update
    public CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);

    //delete
    public void deleteCategory(Integer categoryId);

    //get by id
    public CategoryDto getCategory(Integer categoryId);

    //get all
    public List<CategoryDto> getCategories();
}
