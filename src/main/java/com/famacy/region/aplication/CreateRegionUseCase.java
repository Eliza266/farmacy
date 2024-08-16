package com.famacy.region.aplication;

import com.famacy.region.domain.Region;
import com.famacy.region.domain.RegionService;

public class CreateRegionUseCase {
    private final RegionService regionService;

    public CreateRegionUseCase(RegionService regionService) {
        this.regionService = regionService;
    }

    public void execute(Region region){
        regionService.createRegion(region);
    }

}
