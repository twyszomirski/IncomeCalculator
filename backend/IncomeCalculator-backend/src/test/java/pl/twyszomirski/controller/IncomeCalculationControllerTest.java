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
import pl.twyszomirski.dto.IncomeCalculationRequestDto;
import pl.twyszomirski.dto.IncomeCalculationResponseDto;
import pl.twyszomirski.service.IncomeCalculationService;
import pl.twyszomirski.util.TestUtils;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
        IncomeCalculationRequestDto incomeCalculationRequestDtoPL = new IncomeCalculationRequestDto();
        incomeCalculationRequestDtoPL.setDailyRate(1.0f);
        incomeCalculationRequestDtoPL.setCountryCode("PL");

        IncomeCalculationResponseDto incomeCalculationResponseDtoPL = new IncomeCalculationResponseDto();
        incomeCalculationResponseDtoPL.setAdditionalCost(11L);
        incomeCalculationResponseDtoPL.setMonthlyRate(19f);
        incomeCalculationResponseDtoPL.setMonthlyTax(15.5f);
        when(incomeCalculationService.calculateIncome(1.0f, "PL")).thenReturn(incomeCalculationResponseDtoPL);

        mockMvc.perform(post("/incomeCalculations").content(TestUtils.asJsonString(incomeCalculationRequestDtoPL))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect( jsonPath("$.additionalCost", is(11)))
                .andExpect( jsonPath("$.monthlyRate", is(19.0)))
                .andExpect( jsonPath("$.monthlyTax", is(15.5)));

        IncomeCalculationRequestDto incomeCalculationRequestDtoDE = new IncomeCalculationRequestDto();
        incomeCalculationRequestDtoDE.setDailyRate(2.0f);
        incomeCalculationRequestDtoDE.setCountryCode("DE");

        IncomeCalculationResponseDto incomeCalculationResponseDtoDE = new IncomeCalculationResponseDto();
        incomeCalculationResponseDtoDE.setAdditionalCost(111L);
        incomeCalculationResponseDtoDE.setMonthlyRate(119f);
        incomeCalculationResponseDtoDE.setMonthlyTax(115.5f);
        when(incomeCalculationService.calculateIncome(2.0f, "DE")).thenReturn(incomeCalculationResponseDtoDE);

        mockMvc.perform(post("/incomeCalculations").content(TestUtils.asJsonString(incomeCalculationRequestDtoDE))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect( jsonPath("$.additionalCost", is(111)))
                .andExpect( jsonPath("$.monthlyRate", is(119.0)))
                .andExpect( jsonPath("$.monthlyTax", is(115.5)));

        //TODO: test for when the response is not ok
    }

}