package com.famacy.activeprinciple.aplication;

import java.util.List;

import com.famacy.activeprinciple.domain.Activeprinciple;
import com.famacy.activeprinciple.domain.ActiveprincipleService;

public class FindAllActiveprincipleUseCase {
   private final ActiveprincipleService activeprincipleService;

    public FindAllActiveprincipleUseCase(ActiveprincipleService activeprincipleService) {
        this.activeprincipleService = activeprincipleService;
    }


    public List<Activeprinciple> execute(){
        return activeprincipleService.findAllActiveprinciple();
    }

}
