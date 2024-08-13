package com.famacy.country.aplication;

import com.famacy.country.domain.Country;
import com.famacy.country.domain.CountryService;

public class CreateCountryUseCase {
    private final CountryService countryService;

    public CreateCountryUseCase(CountryService countryService) {
        this.countryService = countryService;
    }

    public void execute(Country country){
        countryService.createCountry(country);
    }

}
