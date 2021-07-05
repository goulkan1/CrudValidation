package com.example.demo.models.repos;

import com.example.demo.models.entities.Supplier;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

// Crud Repo
// Paging Repo
// Jpa Repo
public interface SupplierRepo extends CrudRepository<Supplier, Long> {

    Supplier findByEmail(String email);

    List<Supplier> findByNameContainsOrderByIdDesc(String name);

    List<Supplier> findByNameStartingWith(String prefix);

    List<Supplier> findByNameContainsOrEmailContains(String name, String email);

}
