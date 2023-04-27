package com.example.pi_project.services;

import com.example.pi_project.entities.*;
import com.example.pi_project.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImp implements  ProductService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(int idProduct) {
        productRepository.deleteById(idProduct);
    }

    @Override
    public void RateProduct(Product product, int rating, int id) {

    }

    @Override
    public List<Product> search(String keyword, BigDecimal minPrice, BigDecimal maxPrice) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> query = builder.createQuery(Product.class);
        Root<Product> root = query.from(Product.class);

        Predicate keywordPredicate = builder.or(
                builder.like(root.get("name"), "%" + keyword + "%"),
                builder.like(root.get("description"), "%" + keyword + "%")
        );
        Predicate pricePredicate = builder.between(root.get("price"), minPrice, maxPrice);

        query.where(builder.and(keywordPredicate, pricePredicate));

        return entityManager.createQuery(query).getResultList();
    }


    @Override
    public String getMostFrequentCategory() {
        List<Product> products = productRepository.findAll();
        List<Category> categories = categoryRepository.findAll();
        Map<Category, Integer> categoryCount = new HashMap<>();

        // Compter le nombre de produits dans chaque catégorie
        for (Product product : products) {
            Category category = product.getCategory();
            if (categoryCount.containsKey(category)) {
                int count = categoryCount.get(category);
                categoryCount.put(category, count + 1);
            } else {
                categoryCount.put(category, 1);
            }
        }

        // Trouver la catégorie la plus fréquente
        Category mostFrequentCategory = null;
        int maxCount = 0;
        for (Map.Entry<Category, Integer> entry : categoryCount.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mostFrequentCategory = entry.getKey();
            }
        }

        // Calculer le pourcentage de produits dans la catégorie la plus fréquente
        double percentage = ((double) maxCount / (double) products.size()) * 100;

        return "La catégorie la plus fréquente est " + mostFrequentCategory.getName() + " avec " + percentage + "% des produits.";
    }



    @Override
    public double getTotalPrice() {
        List<Product> products = productRepository.findAll();

        // Calculer la somme des prix des produits
        double totalPrice = 0;
        for (Product product : products) {
            totalPrice += product.getNumber() * product.getPrice();
        }


        return totalPrice;
    }


    @Override
    public double getProductPricePercentage(int idproduct) {
        Product product = productRepository.findById(idproduct)
                .orElseThrow(() -> new IllegalArgumentException("Produit non trouvé avec l'ID: " + idproduct));

        double totalPrice = getTotalPrice();

        double productPrice = product.getPrice() * product.getNumber();
        double productPricePercentage = productPrice / totalPrice * 100;
        return productPricePercentage;
    }


    @Override
    public Map<String, Double> getAveragePricesByCategory() {
        List<Category> categories = categoryRepository.findAll();
        Map<String, Double> result = new HashMap<>();

        for (Category category : categories) {
            List<Product> products = productRepository.findByCategory(category);
            double total = 0;
            int count = 0;
            for (Product product : products) {
                total += product.getPrice();
                count++;
            }
            double average = count > 0 ? total / count : 0;
            result.put(category.getName(), average);
        }

        return result;
    }






    @Override
    public List<Product> findProductsByCategoryAndNumberLessThan(Category category, int number) {
        return productRepository.findByCategoryAndNumberLessThan(category, number);
    }
}






