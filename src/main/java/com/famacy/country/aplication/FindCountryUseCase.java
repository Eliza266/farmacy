package com.famacy.country.aplication;

import java.util.Optional;

import com.famacy.country.domain.Country;
import com.famacy.country.domain.CountryService;

public class FindCountryUseCase {
    private final CountryService countryService;

        public FindCountryUseCase(CountryService countryService) {
        this.countryService = countryService;
    }
    public Optional<Country> execute(String codeCountry){
            return countryService.findCountryById(codeCountry);
        }


}
