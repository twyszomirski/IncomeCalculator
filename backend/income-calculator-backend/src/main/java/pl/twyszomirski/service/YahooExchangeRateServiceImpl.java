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
 * Implementation for pl.twyszomirski.service.ExchangeRateService
 */
@Service
public class YahooExchangeRateServiceImpl implements ExchangeRateService{

    private static final Logger LOGGER = LoggerFactory.getLogger(YahooExchangeRateServiceImpl.class);

    /**
     * Placeholder for 'To' currency. Later replaced in the url.
     */
    private static final String TO_CURRENCY_PLACE_HOLDER = "__TO_CURRENCY__";

    /**
     * Placeholder for 'From' currency. Later replaced in the url.
     */
    private static final String FROM_CURRENCY_PLACE_HOLDER = "__FROM_CURRENCY__";

    /**
     * Url for Yahoo finance service providing exchange rates
     */
    private static final String EXCHANGE_RATE_SERVICE_URL = "http://query.yahooapis.com/v1/public/yql?q=select * from yahoo.finance.xchange where pair in (\"" + TO_CURRENCY_PLACE_HOLDER +
            FROM_CURRENCY_PLACE_HOLDER+ "\")&env=store://datatables.org/alltableswithkeys";

    /**
     * {@inheritDoc}
     */
    @Override
    @Cacheable("exchangeRates")
    public BigDecimal getCurrentExchangeRate(ExchangeRateToken exchangeRateToken) throws NoExchangeRateException{

        LOGGER.debug("Calling getCurrentExchangeRate with parameters {}",exchangeRateToken);

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<?> request = new HttpEntity<Object>(headers);

        String url= EXCHANGE_RATE_SERVICE_URL.replaceAll(FROM_CURRENCY_PLACE_HOLDER, exchangeRateToken.getFormCurrency())
                .replaceAll(TO_CURRENCY_PLACE_HOLDER, exchangeRateToken.getToCurrency());

        BigDecimal result = null;
        try {
            ResponseEntity<ExchangeRateDto> response = restTemplate.exchange(url, HttpMethod.GET, request, ExchangeRateDto.class);
            result = new BigDecimal(Float.parseFloat(response.getBody().getQuery().getResults().getRate().getRate()));
        }
        catch(Exception e){
            LOGGER.error("The damn yahoo finance service failed",e);
            throw new NoExchangeRateException(e);
        }
        return result;
    }

}
