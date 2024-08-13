package com.famacy.country.aplication;

import com.famacy.country.domain.CountryService;

public class DeleteCountryUseCase {
    private final CountryService countryService;
    

    public DeleteCountryUseCase(CountryService countryService) {
        this.countryService = countryService;
    }

    public void execute(String codeCountry){

    }
}
