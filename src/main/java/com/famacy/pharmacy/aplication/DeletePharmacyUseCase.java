package com.famacy.pharmacy.aplication;

import com.famacy.pharmacy.domain.PharmacyService;

public class DeletePharmacyUseCase {
    private final PharmacyService pharmacyService;

    public DeletePharmacyUseCase(PharmacyService pharmacyService) {
        this.pharmacyService = pharmacyService;
    }

    public void execute(int idPha) {
        pharmacyService.deletePharmacy(idPha);
    }
}
