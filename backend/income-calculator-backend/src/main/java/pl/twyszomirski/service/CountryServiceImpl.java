package pl.twyszomirski.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.twyszomirski.domain.Country;

import java.awt.print.Pageable;

/**
 * Created by Tomasz
 */
@Service
@Transactional
public class CountryServiceImpl implements CountryService{

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public Iterable<Country> findAll(){
        return countryRepository.findAll();
    }

    @Override
    public Country getByCountryCode(String countryCode) {
        return countryRepository.getByCountryCode(countryCode);
    }
}
