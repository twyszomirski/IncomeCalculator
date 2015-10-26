package pl.twyszomirski.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.twyszomirski.domain.Country;

import java.awt.print.Pageable;

/**
 * Created by Tomasz
 * Implementation for a pl.twyszomirski.service.CountryService
 */
@Service
@Transactional
public class CountryServiceImpl implements CountryService{

    @Autowired
    private CountryRepository countryRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterable<Country> findAll(){
        return countryRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Country getByCountryCode(String countryCode) {
        return countryRepository.getByCountryCode(countryCode);
    }
}
