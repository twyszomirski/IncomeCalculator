package pl.twyszomirski.service;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Tomasz
 * Service for fetching exchange rates
 */
public interface ExchangeRateService {

    /**
     * Provides an exchange rate for given value pair and a date.
     * The concrete implementation should use ExchangeRateToken to cache the
     * reponses for a current day.
     * @param exchangeRateToken
     * @return
     * @throws NoExchangeRateException
     */
    BigDecimal getCurrentExchangeRate(ExchangeRateToken exchangeRateToken) throws NoExchangeRateException;
}
