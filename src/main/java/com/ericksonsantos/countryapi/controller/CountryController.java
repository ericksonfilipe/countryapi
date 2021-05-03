package com.ericksonsantos.countryapi.controller;

import com.ericksonsantos.countryapi.dto.CountryDTO;
import com.ericksonsantos.countryapi.dto.ListCountriesDTO;
import com.ericksonsantos.countryapi.service.CountryRestApiConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
public class CountryController {

    private final CountryRestApiConsumer countriesService;

    @Autowired
    public CountryController(CountryRestApiConsumer countriesService) {
        this.countriesService = countriesService;
    }

    @GetMapping("/countries")
    public ResponseEntity<ListCountriesDTO> getAllCountries() {
        ListCountriesDTO countries = new ListCountriesDTO();
        countries.setCountries(Arrays.asList(countriesService.getCountries()));
        return ResponseEntity.ok(countries);
    }

    @GetMapping("/countries/{name}")
    public ResponseEntity<CountryDTO> getAllCountries(@PathVariable(value="name") String name) {
        return ResponseEntity.ok(countriesService.getCountry(name));
    }
}
