package com.famacy.laboratory.domain;
import java.util.List;
import java.util.Optional;

public interface LaboratoryService {
    void createLaboratory(Laboratory laboratory);
    void updateLaboratory(Laboratory laboratory);
    void deleteLaboratory(int idLab);
    Optional<Laboratory> findLaboratoryById(int idLab);
    List<Laboratory> findAllLaboratory();
}
