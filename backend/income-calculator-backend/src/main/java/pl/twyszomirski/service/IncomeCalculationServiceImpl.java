package pl.twyszomirski.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.twyszomirski.domain.Country;
import pl.twyszomirski.dto.IncomeCalculationResponseDto;
import pl.twyszomirski.util.MathUtils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Tomasz
 */
@Service
public class IncomeCalculationServiceImpl implements IncomeCalculationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IncomeCalculationServiceImpl.class);

    /**
     * Number of days in a month
     */
    private static final BigDecimal NUMBER_OF_DAYS_IN_MONTH = new BigDecimal("22");

    /**
     * Polish currency code
     */
    private static final String CURRENCY_CODE_PL = "PLN";

    @Autowired
    private ExchangeRateService exchangeRateService;

    @Autowired
    private CountryService countryService;

    @Override
    public IncomeCalculationResponseDto calculateIncome(BigDecimal dailyRate, String countryCode) throws NoExchangeRateException{
        LOGGER.trace("Calculating income for rate {} and country {}",dailyRate, countryCode);
        Country country = lookupCountry(countryCode);
        if(country == null){
            LOGGER.error("Error looking up country {}", countryCode);
            throw new IllegalArgumentException("Can't find country for code: " + countryCode);
        }
	ExchangeRateToken exchangeRateToken = new ExchangeRateToken(CURRENCY_CODE_PL,country.getCurrencyCode(),new Date());
        BigDecimal exchangeRate =  exchangeRateService.getCurrentExchangeRate(new ExchangeRateToken(CURRENCY_CODE_PL,country.getCurrencyCode(),new Date()));
        if(exchangeRate == null){
            //just to be on the safe side
            LOGGER.error("Exchange rate was null {}", exchangeRateToken);
            throw new NoExchangeRateException();
        }
        IncomeCalculationResponseDto result = new IncomeCalculationResponseDto();
        BigDecimal monthlyRateGross = NUMBER_OF_DAYS_IN_MONTH.multiply(dailyRate.multiply(exchangeRate));
        BigDecimal monthlyTax = monthlyRateGross.multiply(country.getTaxRate());
        BigDecimal additionalCost = country.getAdditionalCost().multiply(exchangeRate);
        BigDecimal monthlyRateNet = monthlyRateGross.subtract(monthlyTax).subtract(additionalCost);
        result.setMonthlyRate(MathUtils.roundTwoPlaces(monthlyRateNet));
        result.setMonthlyTax(MathUtils.roundTwoPlaces(monthlyTax));
        result.setAdditionalCost(MathUtils.roundTwoPlaces(additionalCost));
        LOGGER.trace("Returning calculation result {}", result);
        return result;
    }

    private Country lookupCountry(String countryCode){
        return countryService.getByCountryCode(countryCode);
    }
}
