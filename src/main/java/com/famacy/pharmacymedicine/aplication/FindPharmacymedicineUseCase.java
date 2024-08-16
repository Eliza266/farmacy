package com.famacy.pharmacymedicine.aplication;

import java.util.Optional;

import com.famacy.pharmacymedicine.domain.Pharmacymedicine;
import com.famacy.pharmacymedicine.domain.PharmacymedicineService;

public class FindPharmacymedicineUseCase {
    private final PharmacymedicineService pharmacymedicineService;

        public FindPharmacymedicineUseCase(PharmacymedicineService pharmacymedicineService) {
        this.pharmacymedicineService = pharmacymedicineService;
    }
    public Optional<Pharmacymedicine> execute(int idMed, int idPha ){
            return pharmacymedicineService.findPharmacymedicineById(idMed, idPha);
        }


}
