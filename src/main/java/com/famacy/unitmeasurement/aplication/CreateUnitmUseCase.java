package com.famacy.unitmeasurement.aplication;

import com.famacy.unitmeasurement.domain.UnitmService;
import com.famacy.unitmeasurement.domain.Unitm;

public class CreateUnitmUseCase {
    private final UnitmService unitService;

    public CreateUnitmUseCase(UnitmService unitService) {
        this.unitService = unitService;
    }


    public void execute(Unitm unitm){
        unitService.createUnitm(unitm);
    }

}
