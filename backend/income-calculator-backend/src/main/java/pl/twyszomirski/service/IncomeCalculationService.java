package pl.twyszomirski.service;

import pl.twyszomirski.dto.IncomeCalculationResponseDto;

import java.math.BigDecimal;

/**
 * Created by Tomasz
 * Service for calculating the income
 */
public interface IncomeCalculationService {

    /**
     * Calculates the monthly income in given currency and for a given daily rate.
     * @param dailyRate the daily rate
     * @param countryCode the code identifying country
     * @return Dto with the result of income calculation
     * @throws NoExchangeRateException
     */
    IncomeCalculationResponseDto calculateIncome(BigDecimal dailyRate, String countryCode) throws NoExchangeRateException;
}
