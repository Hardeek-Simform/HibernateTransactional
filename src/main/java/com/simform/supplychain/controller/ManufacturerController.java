package com.simform.supplychain.controller;

import com.simform.supplychain.entity.Manufacturer;
import com.simform.supplychain.service.impl.ManufacturerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/manufacturer")
public class ManufacturerController {
    /**
     * R = only manufacturer based on cid and id
     */

    @Autowired
    ManufacturerServiceImpl manufacturerService;

    @GetMapping("/cid/{cid}")
    public ResponseEntity<List<Manufacturer>> getManufacturerByCid(@PathVariable int cid) {
        List<Manufacturer> manufacturerListByCid = manufacturerService.findManufacturerListByCid(cid);
        return new ResponseEntity<>(manufacturerListByCid, HttpStatus.FOUND);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Manufacturer> getManufacturerById(@PathVariable int id) {
        Manufacturer manufacturerById = manufacturerService.getManufacturerById(id);
        return new ResponseEntity<>(manufacturerById, HttpStatus.FOUND);
    }

}
