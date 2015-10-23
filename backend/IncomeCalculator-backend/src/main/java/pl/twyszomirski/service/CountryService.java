package pl.twyszomirski.service;

import org.springframework.data.domain.Page;
import pl.twyszomirski.domain.Country;

import java.awt.print.Pageable;

/**
 * Created by Tomasz
 */
public interface CountryService {

    Iterable<Country> findAll();

    Country getByCountryCode(String countryCode);

}
