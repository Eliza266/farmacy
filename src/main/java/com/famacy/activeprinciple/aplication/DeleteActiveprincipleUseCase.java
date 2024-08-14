package com.famacy.activeprinciple.aplication;
import com.famacy.activeprinciple.domain.ActiveprincipleService;

public class DeleteActiveprincipleUseCase {
   private final ActiveprincipleService activeprincipleService;


    public DeleteActiveprincipleUseCase(ActiveprincipleService activeprincipleService) {
        this.activeprincipleService = activeprincipleService;
    }
   
    public void execute(int idap){
        activeprincipleService.deleteActiveprinciple(idap);
    }
}
