package com.famacy.laboratory.aplication;

import java.util.List;

import com.famacy.laboratory.domain.Laboratory;
import com.famacy.laboratory.domain.LaboratoryService;

public class FindAllLaboratoryUseCase {
    private final LaboratoryService laboratoryService;

    public FindAllLaboratoryUseCase(LaboratoryService laboratoryService) {
        this.laboratoryService = laboratoryService;
    }

    public List<Laboratory> execute(){
        return laboratoryService.findAllLaboratory();
    }

}
