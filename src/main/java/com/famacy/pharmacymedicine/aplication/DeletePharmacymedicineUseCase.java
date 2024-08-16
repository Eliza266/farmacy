package com.famacy.pharmacymedicine.aplication;

import com.famacy.pharmacymedicine.domain.PharmacymedicineService;

public class DeletePharmacymedicineUseCase {
    private final PharmacymedicineService pharmacymedicineService;
    

    public DeletePharmacymedicineUseCase(PharmacymedicineService pharmacymedicineService) {
        this.pharmacymedicineService = pharmacymedicineService;
    }

    public void execute(int idMed, int idPha){
        pharmacymedicineService.deletePharmacymedicine(idMed,idPha);
    }
}
