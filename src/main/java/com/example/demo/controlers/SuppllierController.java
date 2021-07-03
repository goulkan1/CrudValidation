package com.example.demo.controlers;

import com.example.demo.dto.ResponseData;
import com.example.demo.dto.SupplierData;
import com.example.demo.models.entities.Supplier;
import com.example.demo.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/supplliers")
public class SuppllierController {

    @Autowired
    private SupplierService supplierService;


    @PostMapping
    private ResponseEntity<ResponseData<Supplier>> create(@Valid @RequestBody SupplierData supplierData, Errors errors) {

        // KALO GAGAL DISINI
        ResponseData<Supplier> responseData = new ResponseData<>();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessage().add((error.getDefaultMessage()));
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        // KALO BERHASIL
        Supplier supplier = new Supplier();
        supplier.setName(supplierData.getName());
        supplier.setAddress(supplierData.getAddress());
        supplier.setEmail(supplierData.getEmail());
        responseData.setStatus(true);
        responseData.setPayload(supplierService.save(supplier));
        return ResponseEntity.ok(responseData);
    }
}
