package pl.twyszomirski.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.twyszomirski.domain.Country;
import pl.twyszomirski.dto.IncomeCalculationResponseDto;
import pl.twyszomirski.util.EntityUtils;

import java.math.BigDecimal;
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
                EntityUtils.buildCountry("US","USD", "USA", new BigDecimal("0.2"),new BigDecimal("10.0")));
        when(exchangeRateService.getCurrentExchangeRate(new ExchangeRateToken("PLN","USD", new Date()))).thenReturn(new BigDecimal("2.0"));

        IncomeCalculationResponseDto result = service.calculateIncome(new BigDecimal("15.0"),"US");

        assertThat(result.getMonthlyRate(),is(new BigDecimal("508.00")));
        assertThat(result.getMonthlyTax(),is(new BigDecimal("132.00")));
        assertThat(result.getAdditionalCost(),is(new BigDecimal("20.00")));
    }

    @Test(expected = NoExchangeRateException.class)
    public void testCalculateIncomePassesException() throws Exception {
        when(countryService.getByCountryCode(anyString())).thenReturn(
                EntityUtils.buildCountry("US","USD", "USA", new BigDecimal("0.19"),new BigDecimal("10.0")));
        when(exchangeRateService.getCurrentExchangeRate(new ExchangeRateToken("PLN","USD", new Date()))).thenThrow(new NoExchangeRateException());

        service.calculateIncome(new BigDecimal("15.0"), "US");
    }

    @Test(expected = NoExchangeRateException.class)
    public void testCalculateIncomeNullRate() throws Exception {
        when(countryService.getByCountryCode(anyString())).thenReturn(
                EntityUtils.buildCountry("US","USD", "USA", new BigDecimal("0.19"),new BigDecimal("10.0")));
        when(exchangeRateService.getCurrentExchangeRate(new ExchangeRateToken("PLN","USD", new Date()))).thenReturn(null);

        service.calculateIncome(new BigDecimal("15"), "US");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateIncomeNullCountry() throws Exception {
        service.calculateIncome(new BigDecimal("15"), "US");
    }
}