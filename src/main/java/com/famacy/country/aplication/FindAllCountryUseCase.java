package com.famacy.country.aplication;

import java.util.List;

import com.famacy.country.domain.Country;
import com.famacy.country.domain.CountryService;

public class FindAllCountryUseCase {
    private final CountryService countryService;

    public FindAllCountryUseCase(CountryService countryService) {
        this.countryService = countryService;
    }

    public List<Country> execute(){
        return countryService.findAllCountry();
    }

}
