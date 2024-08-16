package com.famacy.pharmacymedicine.domain;
import java.util.List;
import java.util.Optional;

public interface PharmacymedicineService {
    void createPharmacymedicine(Pharmacymedicine pharmacymedicine);
    void updatePharmacymedicine(Pharmacymedicine pharmacymedicine);
    void deletePharmacymedicine(int idMed, int idPha);
    Optional<Pharmacymedicine> findPharmacymedicineById(int idMed, int idPha);
    List<Pharmacymedicine> findAllPharmacymedicine();
}
