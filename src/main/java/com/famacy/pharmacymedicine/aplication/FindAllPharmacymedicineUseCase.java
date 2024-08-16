package com.famacy.pharmacymedicine.aplication;

import java.util.List;

import com.famacy.pharmacymedicine.domain.Pharmacymedicine;
import com.famacy.pharmacymedicine.domain.PharmacymedicineService;

public class FindAllPharmacymedicineUseCase {
    private final PharmacymedicineService pharmacymedicineService;

    public FindAllPharmacymedicineUseCase(PharmacymedicineService pharmacymedicineService) {
        this.pharmacymedicineService = pharmacymedicineService;
    }

    public List<Pharmacymedicine> execute(){
        return pharmacymedicineService.findAllPharmacymedicine();
    }

}
