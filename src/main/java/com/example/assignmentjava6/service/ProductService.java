package com.example.assignmentjava6.service;

import com.example.assignmentjava6.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();

    Product findById(Integer id);

    List<Product> findByCategory(String id);

    Product create(Product product);

    Product update(Product product);

    void delete(Integer id);
}
