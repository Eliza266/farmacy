package com.famacy.pharmacy.domain;

import java.util.List;
import java.util.Optional;

public interface PharmacyService {
    void createPharmacy(Pharmacy pharmacy);
    void updatePharmacy(Pharmacy pharmacy);
    void deletePharmacy(int idPha);
    Optional<Pharmacy> findPharmacyById(int idPha);
    List<Pharmacy> findAllPharmacy();
}
