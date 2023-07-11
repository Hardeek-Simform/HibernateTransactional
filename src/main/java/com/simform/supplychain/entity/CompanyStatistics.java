package com.simform.supplychain.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CompanyStatistics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int manufacturerCount;
    private int supplierCount;
    @OneToOne
    @JoinColumn(name = "company_fk_cid")
    private Company company;
}
