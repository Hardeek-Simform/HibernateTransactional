package com.simform.supplychain.controller;

import com.simform.supplychain.entity.Suppliers;
import com.simform.supplychain.repository.SupplierRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/practical")
public class Practical3Controller {
    @Autowired
    private SupplierController supplierController;

    @Autowired
    private SupplierRepository supplierRepository;

    @GetMapping("/fail")
    public ResponseEntity<List<Suppliers>> failWithoutTransactional() {
        // here consider we will be adding multiple suppliers and that would fail in between
        Suppliers s1 = new Suppliers();
        s1.setSupplierName("Failed but added");
        s1.setSid(10001);
        s1.setContactNo("90090909");
        supplierRepository.save(s1);
        Suppliers s2 = null;
        supplierRepository.save(s2);
        return supplierController.getAllSuppliers();
    }

    @Transactional
    @GetMapping("/pass")
    public ResponseEntity<List<Suppliers>> passWithTransactional() {
        // here consider we will be adding multiple suppliers and that would fail in between
        Suppliers s1 = new Suppliers();
        s1.setSupplierName("Passed person");
        s1.setSid(10001);
        s1.setContactNo("90090909");
        supplierRepository.save(s1);
        Suppliers s2 = null;
        supplierRepository.save(s2);
        return supplierController.getAllSuppliers();
    }

    /**
     * Here the conclusion is here in above example error would be generated while adding supplier s2 because of not unique id. if error occurs then in 1st case (/fail)
     * the 1 field will be added (s1 data will be added). In 2nd case due to @Transactional annotation it won't add s1 and s2 both.
     *
     * Hence, @Transactional means either all would be executed or none will be executed
     */

}
