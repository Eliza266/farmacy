package com.famacy.medicine.aplication;

import com.famacy.medicine.domain.Medicine;
import com.famacy.medicine.domain.MedicineService;

public class CreateMedicineUseCase {
    private final MedicineService medicineService;

    public CreateMedicineUseCase(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    public void execute(Medicine medicine){
        medicineService.createMedicine(medicine);
    }

}
