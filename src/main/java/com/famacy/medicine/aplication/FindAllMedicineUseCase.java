package com.famacy.medicine.aplication;

import java.util.List;

import com.famacy.medicine.domain.Medicine;
import com.famacy.medicine.domain.MedicineService;

public class FindAllMedicineUseCase {
    private final MedicineService medicineService;

    public FindAllMedicineUseCase(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    public List<Medicine> execute(){
        return medicineService.findAllMedicine();
    }

}
