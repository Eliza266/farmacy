package com.famacy.city.aplication;

import com.famacy.city.domain.City;
import com.famacy.city.domain.CityService;

public class CreateCityUseCase {
    private final CityService cityService;

    public CreateCityUseCase(CityService cityService) {
        this.cityService = cityService;
    }

    public void execute(City city){
        cityService.createCity(city);
    }

}
