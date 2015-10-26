package pl.twyszomirski.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.twyszomirski.yahoo.finance.dto.ExchangeRateDto;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by Tomasz
 */
@Service
public class YahooExchangeRateServiceImpl implements ExchangeRateService{

    private static final Logger LOGGER = LoggerFactory.getLogger(YahooExchangeRateServiceImpl.class);

    private static final String DATE_IDENTIFIER_DATE_FORMAT="ddMMyyyy";

    private static final String TO_CURRENCY_PLACE_HOLDER = "__TO_CURRENCY__";

    private static final String FROM_CURRENCY_PLACE_HOLDER = "__FROM_CURRENCY__";

    private static final String EXCHANGE_RATE_SERVICE_URL = "http://query.yahooapis.com/v1/public/yql?q=select * from yahoo.finance.xchange where pair in (\"" + TO_CURRENCY_PLACE_HOLDER +
            FROM_CURRENCY_PLACE_HOLDER+ "\")&env=store://datatables.org/alltableswithkeys";

    @Override
    @Cacheable("exchangeRates")
    public BigDecimal getExchangeRate(String fromCode, String toCode, String dateIdentifier) throws NoExchangeRateException{

        LOGGER.debug("Calling getExchangeRate with parameters {}, {}, {}", fromCode, toCode, dateIdentifier);

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<?> request = new HttpEntity<Object>(headers);

        String url= EXCHANGE_RATE_SERVICE_URL.replaceAll(FROM_CURRENCY_PLACE_HOLDER, fromCode).replaceAll(TO_CURRENCY_PLACE_HOLDER, toCode);

        BigDecimal result = null;
        try {
            ResponseEntity<ExchangeRateDto> response = restTemplate.exchange(url, HttpMethod.GET, request, ExchangeRateDto.class);
            result = BigDecimal.valueOf(Float.parseFloat(response.getBody().getQuery().getResults().getRate().getRate()));
        }
        catch(Exception e){
            LOGGER.error("The damn yahoo finance service failed",e);
            throw new NoExchangeRateException(e);
        }
        return result;
    }

    @Override
    public String getDateToken(Date date) {
        String result = null;
        synchronized (this) {
            result = new SimpleDateFormat(DATE_IDENTIFIER_DATE_FORMAT).format(date);
        }
        return result;
    }
}
