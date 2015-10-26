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
import pl.twyszomirski.dto.IncomeCalculationResponseDto;
import pl.twyszomirski.service.IncomeCalculationService;
import pl.twyszomirski.service.NoExchangeRateException;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Tomasz
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = IncomeCalculator.class)
@WebAppConfiguration
public class IncomeCalculationControllerTest {

    @Mock
    IncomeCalculationService incomeCalculationService;

    @InjectMocks
    private IncomeCalculationController incomeCalculationController;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(incomeCalculationController).build();
    }

    @Test
    public void testCalculateIncome() throws Exception {

        IncomeCalculationResponseDto incomeCalculationResponseDtoPL = new IncomeCalculationResponseDto();
        incomeCalculationResponseDtoPL.setAdditionalCost(new BigDecimal("11.0"));
        incomeCalculationResponseDtoPL.setMonthlyRate(new BigDecimal("19.0"));
        incomeCalculationResponseDtoPL.setMonthlyTax(new BigDecimal("15.5"));
        when(incomeCalculationService.calculateIncome(new BigDecimal("1.0"), "PL")).thenReturn(incomeCalculationResponseDtoPL);

        mockMvc.perform(get("/income-calculations")
                .param("daily_rate","1.0")
                .param("country_code", "PL")
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect( jsonPath("$.additionalCost", is(11.0)))
                .andExpect( jsonPath("$.monthlyRate", is(19.0)))
                .andExpect( jsonPath("$.monthlyTax", is(15.5)));

        IncomeCalculationResponseDto incomeCalculationResponseDtoDE = new IncomeCalculationResponseDto();
        incomeCalculationResponseDtoDE.setAdditionalCost(new BigDecimal("111.0"));
        incomeCalculationResponseDtoDE.setMonthlyRate(new BigDecimal("119.0"));
        incomeCalculationResponseDtoDE.setMonthlyTax(new BigDecimal("115.5"));
        when(incomeCalculationService.calculateIncome(new BigDecimal("2.0"), "DE")).thenReturn(incomeCalculationResponseDtoDE);

        mockMvc.perform(get("/income-calculations")
                .param("daily_rate","2.0")
                .param("country_code", "DE")
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect( jsonPath("$.additionalCost", is(111.0)))
                .andExpect( jsonPath("$.monthlyRate", is(119.0)))
                .andExpect( jsonPath("$.monthlyTax", is(115.5)));

        when(incomeCalculationService.calculateIncome(new BigDecimal("3.0"), "NL")).thenThrow(new NoExchangeRateException());

        mockMvc.perform(get("/income-calculations")
                .param("daily_rate","3.0")
                .param("country_code", "NL")
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().is5xxServerError());
    }

}