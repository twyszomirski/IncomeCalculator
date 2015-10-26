package pl.twyszomirski.service;

import com.google.common.collect.Iterables;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.twyszomirski.IncomeCalculator;
import pl.twyszomirski.domain.Country;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;

/**
 * Created by Tomasz
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = IncomeCalculator.class)
@Sql
public class CountryServiceImplIntegrationTest {

    @Autowired
    private CountryService countryService;

    @Test
    public void testFindAll()  {
        assertThat(Iterables.size(countryService.findAll()), is(2));
    }

    @Test
    public void testGetByCountryCode(){
        Country country = countryService.getByCountryCode("PL");
        assertThat(country, is(notNullValue()));
        assertThat(country.getName(), is("Poland"));

        country = countryService.getByCountryCode("GB");
        assertThat(country, is(notNullValue()));
        assertThat(country.getName(), is("UK"));

        country = countryService.getByCountryCode("NON_EXISTENT");
        assertThat(country, is(nullValue()));
    }
}