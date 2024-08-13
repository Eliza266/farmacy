package com.famacy.modeadmin.aplication;

import com.famacy.modeadmin.domain.Modeadmi;
import com.famacy.modeadmin.domain.ModeadmiService;

public class UpdateModeadmiUseCase {
    private final ModeadmiService modeadmiService;

    public UpdateModeadmiUseCase(ModeadmiService modeadmiService) {
        this.modeadmiService = modeadmiService;
    }

    public void execute (Modeadmi modeadmi){
        modeadmiService.updateModeadmi(modeadmi);
    }

}
