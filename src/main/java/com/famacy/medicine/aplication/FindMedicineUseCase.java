package com.famacy.medicine.aplication;

import java.util.Optional;

import com.famacy.medicine.domain.Medicine;
import com.famacy.medicine.domain.MedicineService;

public class FindMedicineUseCase {
    private final MedicineService medicineService;

        public FindMedicineUseCase(MedicineService medicineService) {
        this.medicineService = medicineService;
    }
    public Optional<Medicine> execute(int idLab){
            return medicineService.findMedicineById(idLab);
        }
}
