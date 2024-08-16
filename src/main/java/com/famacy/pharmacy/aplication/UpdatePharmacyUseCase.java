package com.famacy.pharmacy.aplication;

import com.famacy.pharmacy.domain.Pharmacy;
import com.famacy.pharmacy.domain.PharmacyService;

public class UpdatePharmacyUseCase {
    private final PharmacyService pharmacyService;

    public UpdatePharmacyUseCase(PharmacyService pharmacyService) {
        this.pharmacyService = pharmacyService;
    }

    public void execute(Pharmacy pharmacy) {
        pharmacyService.updatePharmacy(pharmacy);
    }

}
