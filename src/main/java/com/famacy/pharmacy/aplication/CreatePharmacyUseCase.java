package com.famacy.pharmacy.aplication;

import com.famacy.pharmacy.domain.Pharmacy;
import com.famacy.pharmacy.domain.PharmacyService;

public class CreatePharmacyUseCase {
    private final PharmacyService pharmacyService;

    public CreatePharmacyUseCase(PharmacyService pharmacyService) {
        this.pharmacyService = pharmacyService;
    }

    public void execute(Pharmacy pharmacy){
        pharmacyService.createPharmacy(pharmacy);
    }

}
