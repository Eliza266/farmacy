package com.famacy.unitmeasurement.aplication;

import com.famacy.unitmeasurement.domain.UnitmService;
import com.famacy.unitmeasurement.domain.Unitm;

public class UpdateUnitmUseCase {
    private final UnitmService unitService;

    public UpdateUnitmUseCase(UnitmService unitService) {
        this.unitService = unitService;
    }

    public void execute (Unitm unitm){
        unitService.updateUnitm(unitm);
    }

}
