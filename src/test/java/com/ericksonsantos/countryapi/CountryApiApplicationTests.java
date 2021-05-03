package com.ericksonsantos.countryapi;

import com.ericksonsantos.countryapi.dto.CountryDTO;
import com.ericksonsantos.countryapi.dto.CountrySummaryDTO;
import com.ericksonsantos.countryapi.service.CountryRestApiConsumer;
import com.ericksonsantos.countryapi.service.exception.CountryServiceException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class CountryApiApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CountryRestApiConsumer service;

    @Test
    public void testGetCountriesNoElement() throws Exception {
        testGetCountries(new CountrySummaryDTO[]{}, "{countries: []}");
    }

    @Test
    public void testGetCountriesWithElements() throws Exception {
        CountrySummaryDTO brazil = new CountrySummaryDTO("Brazil", "BR");
        CountrySummaryDTO finland = new CountrySummaryDTO("Finland", "FI");
        testGetCountries(new CountrySummaryDTO[]{brazil, finland}, "{countries: [" +
				"{\"name\": \"Brazil\", \"country_code\": \"BR\"}, {\"name\": \"Finland\", \"country_code\": \"FI\"}" +
				"]}");
    }

    @Test
    public void testGetCountrySuccessful() throws Exception {
        CountryDTO brazil = new CountryDTO("Brazil", "BR", "Brasilia", 213803022L,
				"https://random.com/xpto.jpeg");
		when(service.getCountry("Brazil")).thenReturn(brazil);
		this.mockMvc.perform(get("/countries/Brazil")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().json("{\"name\": \"Brazil\", \"country_code\": \"BR\", " +
						"\"capital\": \"Brasilia\", \"population\": 213803022, " +
						"\"flag_file_url\": \"https://random.com/xpto.jpeg\"}"));
    }

    @Test
    public void testGetCountryNotFound() throws Exception {
        when(service.getCountry("invalid")).thenThrow(new CountryServiceException(HttpStatus.NOT_FOUND));
        this.mockMvc.perform(get("/countries/invalid")).andDo(print()).andExpect(status().is(404))
                .andExpect(content().json("{\"status\": 404, \"message\": \"Not Found.\"}"));
    }

    @Test
    public void testServerError() throws Exception {
        when(service.getCountry("BR")).thenThrow(new CountryServiceException(HttpStatus.BAD_GATEWAY));
        this.mockMvc.perform(get("/countries/BR")).andDo(print()).andExpect(status().is(502))
                .andExpect(content().json("{\"status\": 502, " +
						"\"message\": \"An error has happened. Please, try again later.\"}"));
    }

    private void testGetCountries(CountrySummaryDTO[] countries, String jsonResponse) throws Exception {
        when(service.getCountries()).thenReturn(countries);
        this.mockMvc.perform(get("/countries")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(jsonResponse));
    }
}
