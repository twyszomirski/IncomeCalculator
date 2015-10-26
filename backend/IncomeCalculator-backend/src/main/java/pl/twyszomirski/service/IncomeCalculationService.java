package pl.twyszomirski.service;

import pl.twyszomirski.dto.IncomeCalculationResponseDto;

import java.math.BigDecimal;

/**
 * Created by Tomasz
 */
public interface IncomeCalculationService {

    IncomeCalculationResponseDto calculateIncome(BigDecimal dailyRate, String countryCode) throws NoExchangeRateException;
}
