package com.example.pi_project.controllers;


import com.example.pi_project.entities.Product;
import com.example.pi_project.repositories.ProductRepository;
import com.example.pi_project.services.ProductServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@AllArgsConstructor
public class ProductController {

    ProductServiceImp ProductService;
    @Autowired
    private ProductRepository productRepository;
    @GetMapping("/AllProduct")
    @ResponseBody
    public List<Product> getAllProduct(){
        return ProductService.getAllProducts();
    }
    @PostMapping("/addProduct")
    @ResponseBody
    public Product addProduct(@RequestBody Product Product) {
        return ProductService.addProduct(Product);
    }
    @PutMapping("/updateProduct")
    @ResponseBody
    public Product updateLocation(@RequestBody Product Product){
        return ProductService.updateProduct(Product);
    }
    @DeleteMapping("/deleteLocation/{idProduct}")
    @ResponseBody
    public void deleteTax(@PathVariable int idProduct){
        ProductService.deleteProduct(idProduct);
    }

    @PutMapping("/rating/{id}/{rating}")
    @ResponseBody
    public void rateProduct(@RequestBody Product p,  @PathVariable int rating,@PathVariable int  id) {
        ProductService.RateProduct(p,rating,id);}

    @GetMapping("/search")
    public List<Product> search(
            @RequestParam(value = "q", required = false) String keyword,
            @RequestParam(value = "min_price", required = false) BigDecimal minPrice,
            @RequestParam(value = "max_price", required = false) BigDecimal maxPrice) {
        return ProductService.search(keyword,minPrice,maxPrice);
    }




}
