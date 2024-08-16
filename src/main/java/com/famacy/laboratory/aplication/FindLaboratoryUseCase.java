package com.famacy.laboratory.aplication;

import java.util.Optional;

import com.famacy.laboratory.domain.Laboratory;
import com.famacy.laboratory.domain.LaboratoryService;

public class FindLaboratoryUseCase {
    private final LaboratoryService laboratoryService;

        public FindLaboratoryUseCase(LaboratoryService laboratoryService) {
        this.laboratoryService = laboratoryService;
    }
    public Optional<Laboratory> execute(int idLab){
            return laboratoryService.findLaboratoryById(idLab);
        }
}
