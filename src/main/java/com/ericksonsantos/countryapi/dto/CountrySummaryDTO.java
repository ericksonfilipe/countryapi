package com.ericksonsantos.countryapi.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CountrySummaryDTO {

    @JsonAlias("name")
    private String name;
    @JsonAlias("alpha2Code")
    private String countryCode;

    public CountrySummaryDTO(String name, String countryCode) {
        this.name = name;
        this.countryCode = countryCode;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("country_code")
    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @Override
    public String toString() {
        return "CountrySummaryDTO{name=" + this.name + "}";
    }

}
