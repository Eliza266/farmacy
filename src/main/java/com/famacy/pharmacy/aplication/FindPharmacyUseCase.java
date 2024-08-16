package com.famacy.pharmacy.aplication;

import java.util.Optional;

import com.famacy.pharmacy.domain.Pharmacy;
import com.famacy.pharmacy.domain.PharmacyService;

public class FindPharmacyUseCase {
    private final PharmacyService pharmacyService;

    public FindPharmacyUseCase(PharmacyService pharmacyService) {
        this.pharmacyService = pharmacyService;
    }

    public Optional<Pharmacy> execute(int idPha) {
        return pharmacyService.findPharmacyById(idPha);
    }
}
