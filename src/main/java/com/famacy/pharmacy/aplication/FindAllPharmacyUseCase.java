package com.famacy.pharmacy.aplication;

import java.util.List;

import com.famacy.pharmacy.domain.Pharmacy;
import com.famacy.pharmacy.domain.PharmacyService;

public class FindAllPharmacyUseCase {
    private final PharmacyService pharmacyService;

    public FindAllPharmacyUseCase(PharmacyService pharmacyService) {
        this.pharmacyService = pharmacyService;
    }

    public List<Pharmacy> execute(){
        return pharmacyService.findAllPharmacy();
    }

}
