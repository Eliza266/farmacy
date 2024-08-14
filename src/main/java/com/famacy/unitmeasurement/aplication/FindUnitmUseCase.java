package com.famacy.unitmeasurement.aplication;

import java.util.Optional;
import com.famacy.unitmeasurement.domain.UnitmService;
import com.famacy.unitmeasurement.domain.Unitm;

public class FindUnitmUseCase {
    private final UnitmService unitService;

    public FindUnitmUseCase(UnitmService unitService) {
        this.unitService = unitService;
    }

    public Optional<Unitm> execute(int idum){
            return unitService.findUnitmById(idum);
        }
}
