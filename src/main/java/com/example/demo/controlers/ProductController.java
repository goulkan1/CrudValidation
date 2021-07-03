package com.example.demo.controlers;

import com.example.demo.models.entities.Product;
import com.example.demo.models.repos.ProductRepo;
import com.example.demo.services.ProductService;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/products")
public class ProductController {

    // TODO Controler memangil service, service memanggil controller
    @Autowired
    private ProductRepo productRepo;

    @PostMapping
    public Product create(@RequestBody Product product) {
        return productRepo.save(product);
    }

    @GetMapping
    public Iterable<Product> findAll() {
        return productRepo.findAll();
    }

    @GetMapping("/{id}")
    public Product findeOne(@PathVariable("id") Long id) {
        Optional<Product> product = productRepo.findById(id);
        if (!product.isPresent()) {
            return null;
        }
        return product.get();
    }

    @PutMapping
    public Product update(@RequestBody Product product) {
        return productRepo.save(product);
    }

    @DeleteMapping("/{id}")
    public void deleteOne(@PathVariable("id") Long id) {
        productRepo.deleteById(id);
    }
}
