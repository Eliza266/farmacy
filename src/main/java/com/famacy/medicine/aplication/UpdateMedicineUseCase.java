package com.famacy.medicine.aplication;

import com.famacy.medicine.domain.Medicine;
import com.famacy.medicine.domain.MedicineService;

public class UpdateMedicineUseCase {
    private final MedicineService medicineService;

    public UpdateMedicineUseCase(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    public void execute (Medicine medicine){
        medicineService.updateMedicine(medicine);
    }

}
