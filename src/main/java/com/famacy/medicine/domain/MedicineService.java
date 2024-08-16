package com.famacy.medicine.domain;
import java.util.List;
import java.util.Optional;

public interface MedicineService {
    void createMedicine(Medicine medicine);
    void updateMedicine(Medicine medicine);
    void deleteMedicine(int idMed);
    Optional<Medicine> findMedicineById(int idMed);
    List<Medicine> findAllMedicine();
}
