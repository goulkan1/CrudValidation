package com.example.demo.models.repos;

import com.example.demo.models.entities.Supplier;
import org.springframework.data.repository.CrudRepository;

public interface SupplierRepo extends CrudRepository<Supplier, Long> {
}
