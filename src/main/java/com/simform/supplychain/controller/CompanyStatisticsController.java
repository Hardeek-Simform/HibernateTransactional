package com.simform.supplychain.controller;

import com.simform.supplychain.entity.CompanyStatistics;
import com.simform.supplychain.service.impl.CompanyStatisticsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/statistics")
public class CompanyStatisticsController {
    @Autowired
    private CompanyStatisticsServiceImpl service;

    @GetMapping
    public List<CompanyStatistics> getAllCompanyStatistics() {
        return service.getAllCompanyStatistics();
    }

    @GetMapping("/{id}")
    public CompanyStatistics getCompanyStatisticsById(@PathVariable int id) {
        return service.getCompanyStatisticsById(id);
    }

}
