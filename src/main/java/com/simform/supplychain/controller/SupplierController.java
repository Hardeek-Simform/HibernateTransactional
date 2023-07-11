package com.simform.supplychain.controller;

import com.simform.supplychain.entity.Suppliers;
import com.simform.supplychain.service.impl.SupplierServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/supplier")
public class SupplierController {
    /**
     * C = create and link company
     * R = combination of supply and company
     * U = only supplier
     * D = only supplier
     */

    @Autowired
    SupplierServiceImpl supplierService;

    @PostMapping("/{cid}")
    public ResponseEntity<Suppliers> addSupplier(@RequestBody Suppliers suppliers, @PathVariable int cid) {
        Suppliers addSupplier = supplierService.addSupplier(suppliers, cid);
        return new ResponseEntity<>(addSupplier, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Suppliers>> getAllSuppliers() {
        List<Suppliers> allSuppliers = supplierService.getAllSuppliers();
        return new ResponseEntity<>(allSuppliers, HttpStatus.FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Suppliers> getSupplierById(@PathVariable int id) {
        Suppliers supplierById = supplierService.getSupplierById(id);
        return new ResponseEntity<>(supplierById, HttpStatus.FOUND);
    }

    @PutMapping
    public ResponseEntity<Suppliers> updateSupplierById(@RequestBody Suppliers suppliers) {
        Suppliers updatedSupplier = supplierService.updateSupplier(suppliers);
        return new ResponseEntity<>(updatedSupplier, HttpStatus.FOUND);
    }

    @DeleteMapping("/{cid}/{sid}")
    public ResponseEntity<String> deleteById(@PathVariable int cid, @PathVariable int sid) {
        supplierService.deleteSupplierById(cid, sid);
        return new ResponseEntity<>("User is deleted successfully", HttpStatus.ACCEPTED);
    }
}
