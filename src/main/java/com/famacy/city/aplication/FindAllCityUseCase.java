package com.famacy.city.aplication;

import java.util.List;

import com.famacy.city.domain.City;
import com.famacy.city.domain.CityService;

public class FindAllCityUseCase {
    private final CityService cityService;

    public FindAllCityUseCase(CityService cityService) {
        this.cityService = cityService;
    }

    public List<City> execute(){
        return cityService.findAllCity();
    }

}
