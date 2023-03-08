package com.example.pi_project.services;


import com.example.pi_project.entities.Category;

import java.util.List;

public interface CategoryService {
    public List<Category> getAllCategories();

    public Category addCategory(Category category);


    public Category updateCategory(Category category);
    public void deleteCategory(int idCategory);
}
