package com.simform.supplychain.service;

import com.simform.supplychain.entity.Suppliers;

import java.util.List;

public interface SupplierInf {

    Suppliers addSupplier(Suppliers suppliers, int id);

    List<Suppliers> getAllSuppliers();

    Suppliers getSupplierById(int id);

    Suppliers updateSupplier(Suppliers suppliers);

    void deleteSupplierById(int id, int sid);
}

