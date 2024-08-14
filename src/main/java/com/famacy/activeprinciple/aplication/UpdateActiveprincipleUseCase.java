package com.famacy.activeprinciple.aplication;

import com.famacy.activeprinciple.domain.Activeprinciple;
import com.famacy.activeprinciple.domain.ActiveprincipleService;

public class UpdateActiveprincipleUseCase {
    private final ActiveprincipleService activeprincipleService;

    public UpdateActiveprincipleUseCase(ActiveprincipleService activeprincipleService) {
        this.activeprincipleService = activeprincipleService;
    }
    
    public void execute (Activeprinciple activeprinciple){
        activeprincipleService.updateActiveprinciple(activeprinciple);
    }

}
