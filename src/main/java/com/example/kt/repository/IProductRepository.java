package com.example.kt.repository;

import com.example.kt.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IProductRepository extends JpaRepository<Product,Long> {
    List<Product> findAllByNameContains(String name);
}
