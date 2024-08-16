package com.famacy.pharmacymedicine.aplication;

import com.famacy.pharmacymedicine.domain.Pharmacymedicine;
import com.famacy.pharmacymedicine.domain.PharmacymedicineService;

public class CreatePharmacymedicineUseCase {
    private final PharmacymedicineService pharmacymedicineService;

    public CreatePharmacymedicineUseCase(PharmacymedicineService pharmacymedicineService) {
        this.pharmacymedicineService = pharmacymedicineService;
    }

    public void execute(Pharmacymedicine pharmacymedicine){
        pharmacymedicineService.createPharmacymedicine(pharmacymedicine);
    }

}
