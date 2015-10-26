package pl.twyszomirski.service;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;

import java.util.Date;

import static org.hamcrest.CoreMatchers.is;

/**
 * Created by Tomasz
 */
public class YahooExchangeRateServiceImplIntegrationTest{

    YahooExchangeRateServiceImpl service = new YahooExchangeRateServiceImpl();

    @Test
    public void testGetExchangeRate() throws NoExchangeRateException {
        Assert.assertThat(service.getCurrentExchangeRate(new ExchangeRateToken("PLN", "EUR", new Date())), is(CoreMatchers.notNullValue()));
    }

}