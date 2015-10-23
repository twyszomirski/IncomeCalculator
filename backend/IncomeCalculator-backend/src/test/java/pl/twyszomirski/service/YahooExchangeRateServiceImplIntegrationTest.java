package pl.twyszomirski.service;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

/**
 * Created by Tomasz
 */
public class YahooExchangeRateServiceImplIntegrationTest{

    YahooExchangeRateServiceImpl service = new YahooExchangeRateServiceImpl();

    @Test
    public void testGetExchangeRate() throws NoExchangeRateException {
        Assert.assertThat(service.getExchangeRate("PLN", "EUR", "01012015"), is(CoreMatchers.notNullValue()));
    }

}