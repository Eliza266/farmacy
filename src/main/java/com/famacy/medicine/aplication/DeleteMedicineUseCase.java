package com.famacy.medicine.aplication;

import com.famacy.medicine.domain.MedicineService;

public class DeleteMedicineUseCase {
    private final MedicineService medicineService;
    

    public DeleteMedicineUseCase(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    public void execute(int idMed){
        medicineService.deleteMedicine(idMed);
    }
}
