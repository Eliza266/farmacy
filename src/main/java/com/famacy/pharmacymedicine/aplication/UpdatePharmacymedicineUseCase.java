package com.famacy.pharmacymedicine.aplication;

import com.famacy.pharmacymedicine.domain.Pharmacymedicine;
import com.famacy.pharmacymedicine.domain.PharmacymedicineService;

public class UpdatePharmacymedicineUseCase {
    private final PharmacymedicineService pharmacymedicineService;

    public UpdatePharmacymedicineUseCase(PharmacymedicineService pharmacymedicineService) {
        this.pharmacymedicineService = pharmacymedicineService;
    }

    public void execute (Pharmacymedicine pharmacymedicine){
        pharmacymedicineService.updatePharmacymedicine(pharmacymedicine);
    }

}
