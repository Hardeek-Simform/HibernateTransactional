package com.simform.supplychain.service;

import com.simform.supplychain.entity.Company;

import java.util.List;

public interface CompanyServiceInf {

    Company addCompany(Company company);

    List<Company> getAllCompany();

    Company getCompanyById(int id);

    Company updateCompany(int id, Company company);

    void deleteCompanyById(int id);

}

