package com.famacy.activeprinciple.aplication;

import com.famacy.activeprinciple.domain.Activeprinciple;
import com.famacy.activeprinciple.domain.ActiveprincipleService;

public class CreateActiveprincipleUseCase {
    private final ActiveprincipleService activeprincipleService;


    public CreateActiveprincipleUseCase(ActiveprincipleService activeprincipleService) {
        this.activeprincipleService = activeprincipleService;
    }

    public void execute(Activeprinciple activeprinciple){
        activeprincipleService.createActiveprinciple(activeprinciple);
    }

}
