package com.famacy.country.domain;
import java.util.List;
import java.util.Optional;

public interface CountryService {
    void createCountry(Country country);
    void updateCountry(Country country);
    void deleteCountry(String codeCountry);
    Optional<Country> findCountryById(String idCountry);
    List<Country> findAllCountry();
}
