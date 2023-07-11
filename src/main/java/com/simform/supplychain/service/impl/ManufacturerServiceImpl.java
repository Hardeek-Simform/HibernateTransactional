package com.simform.supplychain.service.impl;

import com.simform.supplychain.entity.Company;
import com.simform.supplychain.entity.Manufacturer;
import com.simform.supplychain.repository.CompanyRepository;
import com.simform.supplychain.repository.ManufacturerRepository;
import com.simform.supplychain.service.ManufacturerInf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerServiceImpl implements ManufacturerInf {
    @Autowired
    private ManufacturerRepository manufacturerRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public List<Manufacturer> findManufacturerListByCid(int cid) {
        Optional<List<Manufacturer>> manufacturers = companyRepository.findAll().stream().filter(company -> company.getCid() == cid).findFirst().map(Company::getManufacturer);
        if (manufacturers.isPresent()) {
            return manufacturers.get();
        } else {
            throw new RuntimeException("cid not found in manufacturer");
        }
    }

    @Override
    public Manufacturer getManufacturerById(int id) {
        Optional<Manufacturer> byId = manufacturerRepository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        } else {
            throw new RuntimeException("id not found in manufacturer");
        }
    }
}
