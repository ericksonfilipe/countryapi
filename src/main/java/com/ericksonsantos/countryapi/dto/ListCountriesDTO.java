package com.ericksonsantos.countryapi.dto;

import java.util.List;

public class ListCountriesDTO {

    private List<CountrySummaryDTO> countries;

    public List<CountrySummaryDTO> getCountries() {
        return countries;
    }

    public void setCountries(List<CountrySummaryDTO> countries) {
        this.countries = countries;
    }
}
