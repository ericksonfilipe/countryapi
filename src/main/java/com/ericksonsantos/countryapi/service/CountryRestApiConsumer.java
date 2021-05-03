package com.ericksonsantos.countryapi.service;

import com.ericksonsantos.countryapi.dto.CountryDTO;
import com.ericksonsantos.countryapi.dto.CountrySummaryDTO;
import com.ericksonsantos.countryapi.service.exception.CountryServiceException;
import com.ericksonsantos.countryapi.service.handler.RestTemplateResponseErrorHandler;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CountryRestApiConsumer {

    private final String URL = "https://restcountries.eu/rest/v2/";

    private final RestTemplate restTemplate;

    public CountryRestApiConsumer(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.errorHandler(new RestTemplateResponseErrorHandler()).build();
    }

    public CountrySummaryDTO[] getCountries() {
        CountrySummaryDTO[] result = restTemplate.getForObject(URL + "all", CountrySummaryDTO[].class);
        return result;
    }

    public CountryDTO getCountry(String name) {
        String url = URL + "/name/" + name + "?fullText=true";
        CountryDTO[] result = restTemplate.getForObject(url, CountryDTO[].class);

        if (result == null || result.length <= 0) {
            throw new CountryServiceException(HttpStatus.NOT_FOUND) ;
        }

        return result[0];
    }
}
