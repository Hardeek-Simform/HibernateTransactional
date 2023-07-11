package com.simform.supplychain.controller;

import com.simform.supplychain.entity.Company;
import com.simform.supplychain.service.impl.CompanyServiceImpl;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {
    /**
     * C = Create company, manufacturer,supplier
     * R = combination of supply,manufacturer and company
     * U = company, manufacturer
     * D = delete from all
     */

    @Autowired
    private CompanyServiceImpl service;

    @Transactional
    @PostMapping
    public ResponseEntity<Company> addCompanyWithManufacturer(@RequestBody Company company) {
        Company addedCompany = service.addCompany(company);
        return new ResponseEntity<>(addedCompany, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Company>> getAllCompany() {
        List<Company> allCompany = service.getAllCompany();
        return new ResponseEntity<>(allCompany, HttpStatus.FOUND);
    }

    @GetMapping("{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable int id) {
        Company companyById = service.getCompanyById(id);
        return new ResponseEntity<>(companyById, HttpStatus.FOUND);
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<Company> updateCompany(@PathVariable int id, @RequestBody Company company) {
        Company updatedCompany = service.updateCompany(id, company);
        return new ResponseEntity<>(updatedCompany, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompanyById(@PathVariable int id) {
        service.deleteCompanyById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
