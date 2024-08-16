package com.famacy.city.aplication;

import com.famacy.city.domain.City;
import com.famacy.city.domain.CityService;

public class UpdateCityUseCase {
    private final CityService cityService;

    public UpdateCityUseCase(CityService cityService) {
        this.cityService = cityService;
    }

    public void execute (City city){
        cityService.updateCity(city);
    }

}
