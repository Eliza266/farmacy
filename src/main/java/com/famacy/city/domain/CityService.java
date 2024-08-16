package com.famacy.city.domain;
import java.util.List;
import java.util.Optional;

public interface CityService {
    void createCity(City city);
    void updateCity(City city);
    void deleteCity(String codCity);
    Optional<City> findCityById(String codCity);
    List<City> findAllCity();
}
