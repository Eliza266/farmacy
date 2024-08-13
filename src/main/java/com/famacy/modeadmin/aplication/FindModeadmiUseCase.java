package com.famacy.modeadmin.aplication;

import java.util.Optional;

import com.famacy.modeadmin.domain.Modeadmi;
import com.famacy.modeadmin.domain.ModeadmiService;

public class FindModeadmiUseCase {
    private final ModeadmiService modeadmiService;


    public FindModeadmiUseCase(ModeadmiService modeadmiService) {
        this.modeadmiService = modeadmiService;
    }
    
    public Optional<Modeadmi> execute(int idap){
            return modeadmiService.findModeadmiById(idap);
        }


}
