package com.famacy.unitmeasurement.aplication;

import java.util.List;
import com.famacy.unitmeasurement.domain.UnitmService;
import com.famacy.unitmeasurement.domain.Unitm;

public class FindAllUnitmUseCase {
   private final UnitmService unitService;

    public FindAllUnitmUseCase(UnitmService unitService) {
        this.unitService = unitService;
    }


    public List<Unitm> execute(){
        return unitService.findAllUnitm();
    }

}
