package pl.twyszomirski.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.twyszomirski.IncomeCalculator;
import pl.twyszomirski.domain.Country;
import pl.twyszomirski.service.CountryService;
import pl.twyszomirski.util.EntityUtils;
import pl.twyszomirski.util.TestUtils;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/**
 * Created by Tomasz
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = IncomeCalculator.class)
@WebAppConfiguration
public class CountryControllerTest {

    @InjectMocks
    private CountryController countryController;

    @Mock
    private CountryService countryService;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(countryController).build();
    }

    @Test
    public void testFindAllCountries() throws Exception {
        Country countryA = EntityUtils.buildCountry("US","USD", "USA", 0.01f,0L);
        Country countryB = EntityUtils.buildCountry("PL","PLN", "Poland", 0.19f,10L);

        when(countryService.findAll()).thenReturn(Arrays.asList(countryA, countryB));

        mockMvc.perform(get("/countries")
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect( jsonPath("$", hasSize(2)))
                .andExpect( jsonPath("$[0].countryCode", is("US")))
                .andExpect( jsonPath("$[0].currencyCode", is("USD")))
                .andExpect( jsonPath("$[0].name", is("USA")))
                .andExpect( jsonPath("$[0].taxRate", is(0.01)))
                .andExpect( jsonPath("$[0].additionalCost", is(0)))
                .andExpect( jsonPath("$[1].countryCode", is("PL")))
                .andExpect( jsonPath("$[1].currencyCode", is("PLN")))
                .andExpect( jsonPath("$[1].name", is("Poland")))
                .andExpect( jsonPath("$[1].taxRate", is(0.19)))
                .andExpect( jsonPath("$[1].additionalCost", is(10)));
    }
}