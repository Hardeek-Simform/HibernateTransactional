package com.simform.supplychain.repository;

import com.simform.supplychain.entity.CompanyStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyStatisticsRepository extends JpaRepository<CompanyStatistics, Integer> {
//    public CompanyStatistics findByCompany(int cid);
//    public void deleteByCompany(Company company);
}
