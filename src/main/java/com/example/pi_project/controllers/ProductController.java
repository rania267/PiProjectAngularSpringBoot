package com.example.pi_project.controllers;


import com.example.pi_project.entities.Category;
import com.example.pi_project.entities.Product;
import com.example.pi_project.repositories.ProductRepository;
import com.example.pi_project.services.ProductService;
import com.example.pi_project.services.ProductServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class ProductController {
    com.example.pi_project.services.ProductService ProductService;

    @GetMapping("/AllProduct")
    @ResponseBody
    public List<Product> getAllProduct() {
        return ProductService.getAllProducts();
    }

    @PostMapping("/addProduct")
    @ResponseBody
    public Product addProduct(@RequestBody Product Product) {
        return ProductService.addProduct(Product);
    }

    @PutMapping("/updateProduct")
    @ResponseBody
    public Product updateLocation(@RequestBody Product Product) {
        return ProductService.updateProduct(Product);
    }

    @DeleteMapping("/deleteLocation/{idProduct}")
    @ResponseBody
    public void deleteTax(@PathVariable int idProduct) {
        ProductService.deleteProduct(idProduct);
    }

    @PutMapping("/rating/{id}/{rating}")
    @ResponseBody
    public void rateProduct(@RequestBody Product p, @PathVariable int rating, @PathVariable int id) {
        ProductService.RateProduct(p, rating, id);
    }

    @GetMapping("/search")
    public List<Product> search(
            @RequestParam(value = "q", required = false) String keyword,
            @RequestParam(value = "min_price", required = false) BigDecimal minPrice,
            @RequestParam(value = "max_price", required = false) BigDecimal maxPrice) {
        return ProductService.search(keyword,minPrice,maxPrice);
    }


    @GetMapping("/getMostFrequentCategory")
    @ResponseBody
    public String getMostFrequentCategory() {
        return ProductService.getMostFrequentCategory();
    }

    @GetMapping("/getTotalPrice")
    @ResponseBody
    public double getTotalPrice() {
        return ProductService.getTotalPrice();
    }

    @GetMapping("/getProductPricePercentage/{idproduct}")
    @ResponseBody
    public double getProductPricePercentage(@PathVariable int idproduct) {
        return ProductService.getProductPricePercentage(idproduct);
    }

    @GetMapping("/average-prices-by-category")
    public ResponseEntity<Map<String, Double>> getAveragePricesByCategory() {
        Map<String, Double> result = ProductService.getAveragePricesByCategory();
        return ResponseEntity.ok(result);
    }

    @ResponseBody
    @GetMapping("/categories/{categoryId}")
    public List<Product> findProductsByCategoryAndNumberLessThan(@PathVariable int categoryId,
                                                                 @RequestParam(defaultValue = "5") int number) {
        Category category = new Category();
        category.setIdCategory(categoryId);
        return ProductService.findProductsByCategoryAndNumberLessThan(category, number);
    }


    ProductRepository productRepository;

    @PostMapping("/products")
    public ResponseEntity<?> createProduct(@RequestParam("image") MultipartFile imageFile, @ModelAttribute Product product) {
        try {
            product.setImage(imageFile.getBytes());
            productRepository.save(product);
            return new ResponseEntity<>("Produit créé avec succès", HttpStatus.CREATED);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Échec de la création du produit", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<byte[]> getProductImage(@PathVariable int id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            byte[] image = productOptional.get().getImage();
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
        }
        return ResponseEntity.notFound().build();
    }

}