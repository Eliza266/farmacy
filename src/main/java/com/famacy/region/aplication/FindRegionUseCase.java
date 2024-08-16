package com.famacy.region.aplication;

import java.util.Optional;

import com.famacy.region.domain.Region;
import com.famacy.region.domain.RegionService;

public class FindRegionUseCase {
    private final RegionService regionService;

        public FindRegionUseCase(RegionService regionService) {
        this.regionService = regionService;
    }
    public Optional<Region> execute(String codeRegion){
            return regionService.findRegionById(codeRegion);
        }


}
