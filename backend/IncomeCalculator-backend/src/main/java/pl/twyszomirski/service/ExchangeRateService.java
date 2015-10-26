package pl.twyszomirski.service;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Tomasz
 */
public interface ExchangeRateService {

    BigDecimal getExchangeRate(String fromCode, String ToCode, String dateIdentifier) throws NoExchangeRateException;

    String getDateToken(Date date);
}
