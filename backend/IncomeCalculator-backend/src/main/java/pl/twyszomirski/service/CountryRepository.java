package pl.twyszomirski.service;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import pl.twyszomirski.domain.Country;

import java.awt.print.Pageable;
import java.util.List;

/**
 * Created by Tomasz
 */
public interface CountryRepository extends CrudRepository<Country, Long> {

    Country getByCountryCode(String countryCode);
}
