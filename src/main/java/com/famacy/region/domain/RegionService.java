package com.famacy.region.domain;
import java.util.List;
import java.util.Optional;

public interface RegionService {
    void createRegion(Region region);
    void updateRegion(Region region);
    void deleteRegion(String codReg);
    Optional<Region> findRegionById(String codReg);
    List<Region> findAllRegion();
}
