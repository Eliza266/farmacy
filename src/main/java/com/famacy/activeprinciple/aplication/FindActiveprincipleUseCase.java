package com.famacy.activeprinciple.aplication;

import java.util.Optional;

import com.famacy.activeprinciple.domain.Activeprinciple;
import com.famacy.activeprinciple.domain.ActiveprincipleService;

public class FindActiveprincipleUseCase {
    private final ActiveprincipleService activeprincipleService;

    public FindActiveprincipleUseCase(ActiveprincipleService activeprincipleService) {
        this.activeprincipleService = activeprincipleService;
    }

    public Optional<Activeprinciple> execute(int idap){
            return activeprincipleService.findActiveprincipleById(idap);
        }


}
