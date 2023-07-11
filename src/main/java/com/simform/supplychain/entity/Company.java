package com.simform.supplychain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Company {
    @Id
    @SequenceGenerator(name = "company_sequence_generator", initialValue = 1001, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company_sequence_generator")
    @Column(name = "c_id")
    private int cid;
    private String brandName;
    @Column(unique = true)
    private String licenseNo;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "company_supplier", joinColumns = @JoinColumn(name = "c_id"), inverseJoinColumns = @JoinColumn(name = "s_id"))
    private List<Suppliers> suppliers;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Manufacturer> manufacturer;
}
