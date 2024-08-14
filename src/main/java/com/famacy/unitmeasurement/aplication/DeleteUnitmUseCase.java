package com.famacy.unitmeasurement.aplication;

import com.famacy.unitmeasurement.domain.UnitmService;

public class DeleteUnitmUseCase {
    private final UnitmService unitService;

    public DeleteUnitmUseCase(UnitmService unitService) {
        this.unitService = unitService;
    }

    public void execute(int idum) {
        unitService.deleteUnitm(idum);
    }
}
