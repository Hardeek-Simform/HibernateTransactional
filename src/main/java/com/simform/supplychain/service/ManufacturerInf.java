package com.simform.supplychain.service;

import com.simform.supplychain.entity.Manufacturer;

import java.util.List;

public interface ManufacturerInf {
    List<Manufacturer> findManufacturerListByCid(int cid);

    Manufacturer getManufacturerById(int id);
}
