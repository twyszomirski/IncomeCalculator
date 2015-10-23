package pl.twyszomirski.service;

import pl.twyszomirski.dto.IncomeCalculationDto;

/**
 * Created by Tomasz
 */
public interface IncomeCalculationService {

    IncomeCalculationDto calculateIncome(Float dailyRate, String countryCode) throws NoExchangeRateException;
}
