package com.famacy.laboratory.aplication;

import com.famacy.laboratory.domain.Laboratory;
import com.famacy.laboratory.domain.LaboratoryService;

public class UpdateLaboratoryUseCase {
    private final LaboratoryService laboratoryService;

    public UpdateLaboratoryUseCase(LaboratoryService laboratoryService) {
        this.laboratoryService = laboratoryService;
    }

    public void execute (Laboratory laboratory){
        laboratoryService.updateLaboratory(laboratory);
    }

}
