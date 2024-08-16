package com.famacy.laboratory.aplication;

import com.famacy.laboratory.domain.LaboratoryService;

public class DeleteLaboratoryUseCase {
    private final LaboratoryService laboratoryService;
    

    public DeleteLaboratoryUseCase(LaboratoryService laboratoryService) {
        this.laboratoryService = laboratoryService;
    }

    public void execute(int idLab){
        laboratoryService.deleteLaboratory(idLab);
    }
}
