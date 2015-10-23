package pl.twyszomirski.service;

import java.util.Date;

/**
 * Created by Tomasz
 */
public interface ExchangeRateService {

    Float getExchangeRate(String fromCode, String ToCode, String dateIdentifier) throws NoExchangeRateException;

    String getDateToken(Date date);
}
