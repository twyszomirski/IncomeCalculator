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

    private static final BigDecimal NUMBER_OF_DAYS_IN_MONTH = new BigDecimal("22");

    private static final String CURRENCY_CODE_PL = "PLN";

    @Autowired
    private ExchangeRateService exchangeRateService;

    @Autowired
    private CountryService countryService;

    @Override
    public IncomeCalculationResponseDto calculateIncome(BigDecimal dailyRate, String countryCode) throws NoExchangeRateException{
        LOGGER.trace("Calculating income for rate {} and country {}",dailyRate, countryCode);
        String dateToken = exchangeRateService.getDateToken(new Date());
        Country country = lookupCountry(countryCode);
        if(country == null){
            LOGGER.error("Error looking up country {}", countryCode);
            throw new IllegalArgumentException("Can't find country for code: " + countryCode);
        }

        BigDecimal exchangeRate =  exchangeRateService.getExchangeRate(CURRENCY_CODE_PL,country.getCurrencyCode(),dateToken);
        if(exchangeRate == null){
            //just to be on the safe side
            LOGGER.error("Exchange rate was null {} {}", countryCode, dateToken);
            throw new NoExchangeRateException();
        }
        IncomeCalculationResponseDto result = new IncomeCalculationResponseDto();
        BigDecimal monthlyRateGross = NUMBER_OF_DAYS_IN_MONTH.multiply(dailyRate.multiply(exchangeRate));
        BigDecimal monthlyTax = monthlyRateGross.multiply(country.getTaxRate());
        BigDecimal monthlyRateNet = monthlyRateGross.subtract(monthlyTax);
        BigDecimal additionalCost = country.getAdditionalCost().multiply(exchangeRate);
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
