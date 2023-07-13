package com.example.assignmentjava6.repository;

import com.example.assignmentjava6.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    @Query(value = "select p from Product p where p.category.id =?1")
    List<Product> findByCategory(String id);

}
