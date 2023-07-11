package com.simform.supplychain.service;

import com.simform.supplychain.entity.Company;
import com.simform.supplychain.entity.CompanyStatistics;

import java.util.List;

public interface CompanyStatisticsInf {
    List<CompanyStatistics> getAllCompanyStatistics();

    CompanyStatistics getCompanyStatisticsById(int id);

    default CompanyStatistics addCompanyStatistic(Company company) {
        CompanyStatistics companyStatistics = new CompanyStatistics();
        companyStatistics.setCompany(company);
        companyStatistics.setSupplierCount((int) company.getSuppliers().stream().count());
        companyStatistics.setManufacturerCount((int) company.getManufacturer().stream().count());
        return companyStatistics;
    }

    default CompanyStatistics updateCompanyStatistic(Company company, List<CompanyStatistics> companyStatistics) {

        for (CompanyStatistics cs : companyStatistics) {
            if (cs.getCompany().getCid() == company.getCid()) {
                cs.setCompany(company);
                cs.setSupplierCount((int) company.getSuppliers().stream().count());
                cs.setManufacturerCount((int) company.getManufacturer().stream().count());
                return cs;
            }
        }
        return null;
    }
}