package com.famacy.laboratory.aplication;

import com.famacy.laboratory.domain.Laboratory;
import com.famacy.laboratory.domain.LaboratoryService;

public class CreateLaboratoryUseCase {
    private final LaboratoryService laboratoryService;

    public CreateLaboratoryUseCase(LaboratoryService laboratoryService) {
        this.laboratoryService = laboratoryService;
    }

    public void execute(Laboratory laboratory){
        laboratoryService.createLaboratory(laboratory);
    }

}
