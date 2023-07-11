package com.simform.supplychain.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Suppliers {
    @Id
    @SequenceGenerator(name = "supplier_sequence", initialValue = 101, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "supplier_sequence")
    @Column(name = "s_id")
    private int sid;
    private String supplierName;
    private String contactNo;

}
