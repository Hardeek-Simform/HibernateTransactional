package com.simform.supplychain.service.impl;

import com.simform.supplychain.entity.CompanyStatistics;
import com.simform.supplychain.repository.CompanyStatisticsRepository;
import com.simform.supplychain.service.CompanyStatisticsInf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyStatisticsServiceImpl implements CompanyStatisticsInf {
    @Autowired
    private CompanyStatisticsRepository companyStatisticsRepository;

    @Override
    public List<CompanyStatistics> getAllCompanyStatistics() {
        return companyStatisticsRepository.findAll();
    }

    @Override
    public CompanyStatistics getCompanyStatisticsById(int id) {
        Optional<CompanyStatistics> byId = companyStatisticsRepository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        } else {
            throw new RuntimeException("Id not found in company statistics");
        }
    }

    public List<CompanyStatistics> findAllCompanyStaticsByCid(int cid) {
        return companyStatisticsRepository.findAllById(Collections.singleton(cid));
    }
}
