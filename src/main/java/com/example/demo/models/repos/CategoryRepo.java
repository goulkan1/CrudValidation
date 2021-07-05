package com.example.demo.models.repos;

import com.example.demo.models.entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CategoryRepo extends PagingAndSortingRepository<Category, Long> {
    //    detail properties page
    Page<Category> findByNameContains(String name, Pageable pageable);
//    tanpa detail properties page
//    List<Category> findByNameContains(String name, Pageable pageable);
}
