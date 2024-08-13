package com.famacy.modeadmin.aplication;

import java.util.List;
import com.famacy.modeadmin.domain.Modeadmi;
import com.famacy.modeadmin.domain.ModeadmiService;

public class FindAllModeadmiUseCase {
   private final ModeadmiService modeadmiService;


    public FindAllModeadmiUseCase(ModeadmiService modeadmiService) {
        this.modeadmiService = modeadmiService;
    }

    public List<Modeadmi> execute(){
        return modeadmiService.findAllModeadmi();
    }

}
