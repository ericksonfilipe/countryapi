package com.ericksonsantos.countryapi.service.handler;

import com.ericksonsantos.countryapi.dto.ErrorResponseDTO;
import com.ericksonsantos.countryapi.service.exception.CountryServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class CountryServiceExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryServiceExceptionHandler.class);

    @ExceptionHandler(value = CountryServiceException.class)
    ResponseEntity<ErrorResponseDTO> handleCountryServiceException(CountryServiceException exception,
                                                                   HttpServletRequest request) {
        LOGGER.error("An error happened while calling {} API", request.getRequestURI());

        if (exception.getStatus() == HttpStatus.NOT_FOUND) {
            return ResponseEntity.status(exception.getStatus().value()).body(
                    new ErrorResponseDTO(exception.getStatus().value(), "Not Found."));
        }

        return ResponseEntity.status(exception.getStatus().value()).body(
                new ErrorResponseDTO(exception.getStatus().value(),
                        "An error has happened. Please, try again later."));
    }

}
