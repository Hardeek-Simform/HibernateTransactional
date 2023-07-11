package com.simform.supplychain.controller;

import com.simform.supplychain.entity.Suppliers;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/practical")
public class Practical3Controller {
    @Autowired
    private SupplierController supplierController;

    @GetMapping("/fail")
    public ResponseEntity<List<Suppliers>> failWithoutTransactional() {
        // here consider we will be adding multiple suppliers and that would fail in between
        Suppliers s1 = new Suppliers();
        s1.setSupplierName("rakesh");
        s1.setSid(10001);
        s1.setContactNo("90090909");
        Suppliers s2 = new Suppliers();
        s2.setSupplierName("rakesh1");
        s2.setSid(10001);               // here the error will be generated
        s2.setContactNo("8899898");
        return supplierController.getAllSuppliers();
    }

    @Transactional
    @GetMapping("/true")
    public ResponseEntity<List<Suppliers>> passWithTransactional() {
        // here consider we will be adding multiple suppliers and that would fail in between
        Suppliers s1 = new Suppliers();
        s1.setSupplierName("rakesh");
        s1.setSid(10001);
        s1.setContactNo("90090909");
        Suppliers s2 = new Suppliers();
        s2.setSupplierName("rakesh1");
        s2.setSid(10001);               // here the error will be generated
        s2.setContactNo("8899898");
        return supplierController.getAllSuppliers();
    }

    /**
     * Here the conclusion is here in above example error would be generated while adding supplier s2 because of not unique id. if error occurs then in 1st case (/fail)
     * the 1 field will be added (s1 data will be added). In 2nd case due to @Transactional annotation it won't add s1 and s2 both.
     *
     * Hence, @Transactional means either all would be executed or none will be executed
     */

}
