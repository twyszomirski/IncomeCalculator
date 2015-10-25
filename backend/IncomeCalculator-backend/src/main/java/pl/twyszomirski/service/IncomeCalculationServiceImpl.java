package pl.twyszomirski.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.twyszomirski.domain.Country;
import pl.twyszomirski.dto.IncomeCalculationResponseDto;

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
    public IncomeCalculationResponseDto calculateIncome(Float dailyRate, String countryCode) throws NoExchangeRateException{
        String dateIdentifier = exchangeRateService.getDateToken(new Date());
        Country country = lookupCountry(countryCode);
        if(country == null){
            throw new IllegalArgumentException("Can't find country for code: " + countryCode);
        }

        Float exchangeRate =  exchangeRateService.getExchangeRate(CURRENCY_CODE_PL,country.getCurrencyCode(),dateIdentifier);
        if(exchangeRate == null){
            //just to be on the safe side
            throw new NoExchangeRateException();
        }
        IncomeCalculationResponseDto result = new IncomeCalculationResponseDto();
        Float monthlyRateGross = NUMBER_OF_DAYS_IN_MONTH* (dailyRate*exchangeRate);
        Float monthlyTax = monthlyRateGross * country.getTaxRate();
        Float monthlyRateNet = monthlyRateGross - monthlyTax;
        result.setMonthlyRate(monthlyRateNet);
        result.setMonthlyTax(monthlyTax);
        result.setAdditionalCost(country.getAdditionalCost() * exchangeRate);
        return result;
    }

    private Country lookupCountry(String countryCode){
        return countryService.getByCountryCode(countryCode);
    }
}
