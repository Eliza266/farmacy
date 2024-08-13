package com.famacy.country.aplication;

import com.famacy.country.domain.Country;
import com.famacy.country.domain.CountryService;

public class UpdateCountryUseCase {
    private final CountryService countryService;

    public UpdateCountryUseCase(CountryService countryService) {
        this.countryService = countryService;
    }

    public void execute (Country country){
        countryService.updateCountry(country);
    }

}
