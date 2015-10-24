package pl.twyszomirski.service;

import pl.twyszomirski.dto.IncomeCalculationResponseDto;

/**
 * Created by Tomasz
 */
public interface IncomeCalculationService {

    IncomeCalculationResponseDto calculateIncome(Float dailyRate, String countryCode) throws NoExchangeRateException;
}
