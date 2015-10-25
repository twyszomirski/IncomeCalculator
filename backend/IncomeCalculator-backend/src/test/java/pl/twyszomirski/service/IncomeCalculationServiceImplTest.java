package pl.twyszomirski.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.twyszomirski.domain.Country;
import pl.twyszomirski.dto.IncomeCalculationResponseDto;
import pl.twyszomirski.util.EntityUtils;

import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

/**
 * Created by Tomasz
 */
@RunWith(MockitoJUnitRunner.class)
public class IncomeCalculationServiceImplTest {

    @InjectMocks
    private IncomeCalculationServiceImpl service;

    @Mock
    private ExchangeRateService exchangeRateService;

    @Mock
    private CountryService countryService;

    @Test
    public void testCalculateIncomeOK() throws Exception {
        when(countryService.getByCountryCode(anyString())).thenReturn(
                EntityUtils.buildCountry("US","USD", "USA", 0.2f,10L));
        when(exchangeRateService.getDateToken(any(Date.class))).thenReturn("01012015");
        when(exchangeRateService.getExchangeRate("PLN","USD", "01012015")).thenReturn(2.0f);

        IncomeCalculationResponseDto result = service.calculateIncome(15f,"US");

        assertThat(result.getMonthlyRate(),is(528f));
        assertThat(result.getMonthlyTax(),is(132f));
        assertThat(result.getAdditionalCost(),is(20f));
    }

    @Test(expected = NoExchangeRateException.class)
    public void testCalculateIncomePassesException() throws Exception {
        when(countryService.getByCountryCode(anyString())).thenReturn(
                EntityUtils.buildCountry("US","USD", "USA", 0.19f,10L));
        when(exchangeRateService.getDateToken(any(Date.class))).thenReturn("01012015");
        when(exchangeRateService.getExchangeRate("PLN","USD", "01012015")).thenThrow(new NoExchangeRateException());

        service.calculateIncome(15f, "US");
    }

    @Test(expected = NoExchangeRateException.class)
    public void testCalculateIncomeNullRate() throws Exception {
        when(countryService.getByCountryCode(anyString())).thenReturn(
                EntityUtils.buildCountry("US","USD", "USA", 0.19f,10L));
        when(exchangeRateService.getDateToken(any(Date.class))).thenReturn("01012015");
        when(exchangeRateService.getExchangeRate("PLN","USD", "01012015")).thenReturn(null);

        service.calculateIncome(15f, "US");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateIncomeNullCountry() throws Exception {
        service.calculateIncome(15f, "US");
    }
}