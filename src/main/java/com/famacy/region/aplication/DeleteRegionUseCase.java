package com.famacy.region.aplication;

import com.famacy.region.domain.RegionService;

public class DeleteRegionUseCase {
    private final RegionService regionService;
    

    public DeleteRegionUseCase(RegionService regionService) {
        this.regionService = regionService;
    }

    public void execute(String codeRegion){
        regionService.deleteRegion(codeRegion);
    }
}
