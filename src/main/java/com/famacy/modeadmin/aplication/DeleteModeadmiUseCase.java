package com.famacy.modeadmin.aplication;

import com.famacy.modeadmin.domain.ModeadmiService;

public class DeleteModeadmiUseCase {
    private final ModeadmiService modeadmiService;

    public DeleteModeadmiUseCase(ModeadmiService modeadmiService) {
        this.modeadmiService = modeadmiService;
    }
    public void execute(int idap){
        modeadmiService.deleteModeadmi(idap);
    }
}
