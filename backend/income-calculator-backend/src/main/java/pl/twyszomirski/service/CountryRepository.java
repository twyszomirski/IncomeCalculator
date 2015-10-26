package pl.twyszomirski.service;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import pl.twyszomirski.domain.Country;

import java.awt.print.Pageable;
import java.util.List;

/**
 * Created by Tomasz
 * Repository for managing countries
 */
public interface CountryRepository extends CrudRepository<Country, Long> {

    /**
     * Returns country with matching countryCode or null if match wasn't find
     * @param countryCode the code identifying  country
     * @return found country or null
     */
    Country getByCountryCode(String countryCode);
}
