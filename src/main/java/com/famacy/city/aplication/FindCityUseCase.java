package com.famacy.city.aplication;

import java.util.Optional;

import com.famacy.city.domain.City;
import com.famacy.city.domain.CityService;

public class FindCityUseCase {
    private final CityService cityService;

        public FindCityUseCase(CityService cityService) {
        this.cityService = cityService;
    }
    public Optional<City> execute(String codCity){
            return cityService.findCityById(codCity);
        }


}
