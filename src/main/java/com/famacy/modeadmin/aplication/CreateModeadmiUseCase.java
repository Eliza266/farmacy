package com.famacy.modeadmin.aplication;

import com.famacy.modeadmin.domain.Modeadmi;
import com.famacy.modeadmin.domain.ModeadmiService;

public class CreateModeadmiUseCase {
    private final ModeadmiService modeadmiService;


    public CreateModeadmiUseCase(ModeadmiService modeadmiService) {
        this.modeadmiService = modeadmiService;
    }
   

    public void execute(Modeadmi modeadmi){
        modeadmiService.createModeadmi(modeadmi);
    }

}
