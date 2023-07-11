package com.simform.supplychain.service.impl;

import com.simform.supplychain.entity.Company;
import com.simform.supplychain.entity.CompanyStatistics;
import com.simform.supplychain.entity.Suppliers;
import com.simform.supplychain.repository.CompanyRepository;
import com.simform.supplychain.repository.CompanyStatisticsRepository;
import com.simform.supplychain.repository.SupplierRepository;
import com.simform.supplychain.service.SupplierInf;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierServiceImpl implements SupplierInf {
    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private CompanyStatisticsRepository companyStatisticsRepository;
    @Autowired
    private CompanyRepository companyRepository;

    public static Suppliers getExistingSupplier(Suppliers supplier, List<Suppliers> existingSuppliers) {
        for (Suppliers existingSupplier : existingSuppliers) {
            if (existingSupplier.getSid() == supplier.getSid()) {
                return existingSupplier;
            }
        }
        return null;
    }

    @Transactional
    @Override
    public Suppliers addSupplier(Suppliers suppliers, int cid) {
        Optional<Company> companyRepoById = companyRepository.findById(cid);
        if (companyRepoById.isPresent()) {
            Suppliers savedSupplier = supplierRepository.save(suppliers);
            Company company = companyRepoById.get();
            company.getSuppliers().add(savedSupplier);
            companyRepository.save(company);
            List<CompanyStatistics> companyStatisticsList = companyStatisticsRepository.findAll();
            CompanyStatistics companyStatistics = companyStatisticsList.stream().filter(stats -> stats.getCompany().getCid() == cid).findFirst().get();
            int supplierCount = companyStatistics.getSupplierCount();
            companyStatistics.setSupplierCount(++supplierCount);
            companyStatisticsRepository.save(companyStatistics);
            return savedSupplier;
        } else {
            throw new RuntimeException("Cid not found");
        }
    }

    @Override
    public List<Suppliers> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    @Override
    public Suppliers getSupplierById(int id) {
        Optional<Suppliers> byId = supplierRepository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        } else {
            throw new RuntimeException("Id not found in supplier");
        }
    }

    @Override
    public Suppliers updateSupplier(Suppliers suppliers) {
        Optional<Suppliers> byId = supplierRepository.findById(suppliers.getSid());
        if (byId.isPresent()) {
            Suppliers existingSupplier = byId.get();
            existingSupplier.setSupplierName(suppliers.getSupplierName());
            existingSupplier.setContactNo(suppliers.getContactNo());
            return supplierRepository.save(existingSupplier);
        } else {
            throw new RuntimeException("User not found for update supplier");
        }
    }

    @Transactional
    @Override
    public void deleteSupplierById(int cid, int sid) {
        Optional<Suppliers> optionalSupplier = supplierRepository.findById(sid);
        Optional<Company> companyById = companyRepository.findById(cid);
        if (optionalSupplier.isPresent() && companyById.isPresent()) {

            // deleting from the relationship with company
            Company company = companyById.get();
            Suppliers suppliers = company.getSuppliers().stream().filter(supplier -> supplier.getSid() == sid).findFirst().get();
            company.getSuppliers().remove(suppliers);
            companyRepository.save(company);

            // deleting from the supplier table
            supplierRepository.delete(optionalSupplier.get());

            // reduce count in statistics
            List<CompanyStatistics> companyStatisticsList = companyStatisticsRepository.findAll();
            CompanyStatistics companyStatistics = companyStatisticsList.stream().filter(stats -> stats.getCompany().getCid() == cid).findFirst().get();
            int supplierCount = companyStatistics.getSupplierCount();
            companyStatistics.setSupplierCount(++supplierCount);
            companyStatisticsRepository.save(companyStatistics);
        } else {
            throw new RuntimeException("cid or sid not present so deletion cant occur in supplier");
        }
    }

}
