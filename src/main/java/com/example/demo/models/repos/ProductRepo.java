package com.example.demo.models.repos;

import com.example.demo.models.entities.Product;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepo extends CrudRepository<Product, Long> {

    List<Product> findByNameContains(String name);
}
