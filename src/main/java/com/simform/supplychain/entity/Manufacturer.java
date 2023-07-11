package com.simform.supplychain.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Manufacturer {
    @Id
    @SequenceGenerator(name = "manufacturer_sequence", initialValue = 501, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "manufacturer_sequence")
    private int mid;
    private String managerName;
    private String contactNo;
    private String state;
    private String city;
}
