package pl.twyszomirski.service;

import org.springframework.data.domain.Page;
import pl.twyszomirski.domain.Country;

import java.awt.print.Pageable;

/**
 * Created by Tomasz
 * Service for managing countries
 */
public interface CountryService {

    /**
     * Returns all countries present in the system
     * @return list of countries
     */
    Iterable<Country> findAll();

    /**
     * Returns Country with matching countryCode or null if match wasn't find
     * @param countryCode the code identifying  country
     * @return found country or null
     */
    Country getByCountryCode(String countryCode);

}
