package com.famacy.region.aplication;

import java.util.List;

import com.famacy.region.domain.Region;
import com.famacy.region.domain.RegionService;

public class FindAllRegionUseCase {
    private final RegionService regionService;

    public FindAllRegionUseCase(RegionService regionService) {
        this.regionService = regionService;
    }

    public List<Region> execute(){
        return regionService.findAllRegion();
    }

}
