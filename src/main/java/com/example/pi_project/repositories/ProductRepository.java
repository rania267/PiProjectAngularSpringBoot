package com.example.pi_project.repositories;


import com.example.pi_project.entities.Category;
import com.example.pi_project.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product,Integer> {




        List<Product> findByCategoryAndNumberLessThan(Category category, int number);
        List<Product> findByCategory(Category category);
    }





