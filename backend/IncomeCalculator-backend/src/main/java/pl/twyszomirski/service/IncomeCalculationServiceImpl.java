package pl.twyszomirski.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.twyszomirski.domain.Country;
import pl.twyszomirski.dto.IncomeCalculationDto;

import java.util.Date;

/**
 * Created by Tomasz
 */
@Service
public class IncomeCalculationServiceImpl implements IncomeCalculationService {

    private static final int NUMBER_OF_DAYS_IN_MONTH = 22;

    private static final String CURRENCY_CODE_PL = "PLN";

    @Autowired
    private ExchangeRateService exchangeRateService;

    @Autowired
    private CountryService countryService;

    @Override
    public IncomeCalculationDto calculateIncome(Float dailyRate, String countryCode) throws NoExchangeRateException{
        String dateIdentifier = exchangeRateService.getDateToken(new Date());
        Country country = lookupCountry(countryCode);
        if(country == null){
            throw new IllegalArgumentException("Can't find country for code: " + countryCode);
        }

        Float exchangeRate =  exchangeRateService.getExchangeRate(country.getCurrencyCode(),CURRENCY_CODE_PL,dateIdentifier);
        if(exchangeRate == null){
            //just to be on the safe side
            throw new NoExchangeRateException();
        }
        IncomeCalculationDto result = new IncomeCalculationDto();
        result.setMonthlyRate(NUMBER_OF_DAYS_IN_MONTH* (dailyRate*exchangeRate));
        result.setAdditionalCost(country.getAdditionalCost());
        result.setMonthlyTax(result.getMonthlyRate()* country.getTaxRate());
        return result;
    }

    private Country lookupCountry(String countryCode){
        return countryService.getByCountryCode(countryCode);
    }
}
