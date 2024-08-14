package com.famacy.unitmeasurement.domain;
import java.util.List;
import java.util.Optional;

public interface UnitmService {
    void createUnitm(Unitm Unitm);
    void updateUnitm(Unitm Unitm);
    void deleteUnitm(int idum);
    Optional<Unitm> findUnitmById(int idum);
    List<Unitm> findAllUnitm();
}
