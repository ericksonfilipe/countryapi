package com.ericksonsantos.countryapi.service.exception;

import org.springframework.http.HttpStatus;

public class CountryServiceException extends RuntimeException {

    private HttpStatus status;

    public CountryServiceException(HttpStatus status) {
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
