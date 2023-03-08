package com.example.pi_project.controllers;


import com.example.pi_project.entities.Category;
import com.example.pi_project.services.CategoryServiceImp;
import com.example.pi_project.services.Twilioinitializer;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class CategoryController {

    CategoryServiceImp CategoryService;
    @GetMapping("/AllCategory")
    @ResponseBody
    public List<Category> getAllCategoryn(){
        return CategoryService.getAllCategories();
    }
    @PostMapping("/addCategory")
    @ResponseBody
    public void addCategory (@RequestBody Category Category, Twilioinitializer i) {

         CategoryService.addCategory(Category);
        i.hello(Category.getName());

    }
    @PutMapping("/updateCategory")
    @ResponseBody
    public Category updateLocation(@RequestBody Category  Category){
        return CategoryService.updateCategory(Category);
    }
    @DeleteMapping("/deleteLocation/{idCategory}")
    @ResponseBody
    public void deleteCategory(@PathVariable int idCategory){
        CategoryService.deleteCategory(idCategory);
    }
}
