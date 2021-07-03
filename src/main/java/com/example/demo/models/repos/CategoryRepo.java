package com.example.demo.models.repos;

import com.example.demo.models.entities.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepo extends CrudRepository<Category, Long> {
}
