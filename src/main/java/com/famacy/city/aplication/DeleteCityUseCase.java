package com.famacy.city.aplication;

import com.famacy.city.domain.CityService;

public class DeleteCityUseCase {
    private final CityService cityService;
    

    public DeleteCityUseCase(CityService cityService) {
        this.cityService = cityService;
    }

    public void execute(String codCity){
        cityService.deleteCity(codCity);
    }
}
