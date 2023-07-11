package com.simform.supplychain.service.impl;

import com.simform.supplychain.entity.Company;
import com.simform.supplychain.entity.CompanyStatistics;
import com.simform.supplychain.entity.Suppliers;
import com.simform.supplychain.repository.CompanyRepository;
import com.simform.supplychain.repository.CompanyStatisticsRepository;
import com.simform.supplychain.repository.ManufacturerRepository;
import com.simform.supplychain.repository.SupplierRepository;
import com.simform.supplychain.service.CompanyServiceInf;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyServiceInf {
    @Autowired
    CompanyStatisticsRepository companyStatisticsRepository;
    @Autowired
    SupplierRepository supplierRepository;

    /**
     * C = Create company, manufacturer
     * R = combination of supply,manufacturer and company
     * U = company, manufacturer
     * D = delete from all
     */
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private CompanyStatisticsServiceImpl companyStatisticsService;
    @Autowired
    private ManufacturerRepository manufacturerRepository;

    @Override
    @Transactional
    public Company addCompany(Company company) {
        List<Suppliers> existingSuppliers = supplierRepository.findAllById(company.getSuppliers().stream().map(Suppliers::getSid).collect(Collectors.toList()));
        List<Suppliers> sList = new ArrayList<>();
        for (Suppliers supplier : company.getSuppliers()) {
            Suppliers existingSupplier = SupplierServiceImpl.getExistingSupplier(supplier, existingSuppliers);
            if (existingSupplier != null) {
                existingSupplier.setSupplierName(supplier.getSupplierName());
                existingSupplier.setContactNo(supplier.getContactNo());
                Suppliers save = supplierRepository.save(existingSupplier);
                sList.add(save);

            } else {
                Suppliers save = supplierRepository.save(supplier);
                sList.add(save);
            }
            company.setSuppliers(sList);
        }
        Company savedCompany = companyRepository.save(company);
        CompanyStatistics companyStatistics = companyStatisticsService.addCompanyStatistic(savedCompany);
        companyStatisticsRepository.save(companyStatistics);
        return savedCompany;
    }

    @Override
    public List<Company> getAllCompany() {
        return companyRepository.findAll();
    }

    @Override
    public Company getCompanyById(int id) {
        Optional<Company> companyById = companyRepository.findById(id);
        if (companyById.isPresent()) {
            return companyById.get();
        } else {
            throw new RuntimeException("User missing");
        }
    }

    @Override
    public Company updateCompany(int id, Company company) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (optionalCompany.isPresent()) {
            Company exestingCompany = optionalCompany.get();
            if (!company.getBrandName().isBlank()) {
                exestingCompany.setBrandName(company.getBrandName());
            }
            if (!company.getLicenseNo().isBlank()) {
                exestingCompany.setLicenseNo(company.getLicenseNo());
            }
            if (company.getSuppliers() != null) {
                exestingCompany.setSuppliers(company.getSuppliers());
            }
            if (company.getManufacturer() != null) {
                exestingCompany.setManufacturer(company.getManufacturer());
            }
            return companyRepository.save(exestingCompany);
        } else {
            throw new RuntimeException("User not found");
        }
    }

    @Transactional
    @Override
    public void deleteCompanyById(int id) {
        Optional<Company> optionalCompany = companyRepository.findById(id);

        if (optionalCompany.isPresent()) {
            CompanyStatistics companyStatistics1 = companyStatisticsRepository.findAll().stream().filter(companyStatistics -> companyStatistics.getCompany().getCid() == id).findFirst().get();
            companyStatisticsRepository.delete(companyStatistics1);
            companyRepository.delete(optionalCompany.get());
        } else {
            throw new RuntimeException("Id don't exist so cant be deleted");
        }
    }
}
