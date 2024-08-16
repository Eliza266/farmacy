package com.famacy.region.aplication;

import com.famacy.region.domain.Region;
import com.famacy.region.domain.RegionService;

public class UpdateRegionUseCase {
    private final RegionService regionService;

    public UpdateRegionUseCase(RegionService regionService) {
        this.regionService = regionService;
    }

    public void execute (Region region){
        regionService.updateRegion(region);
    }

}
