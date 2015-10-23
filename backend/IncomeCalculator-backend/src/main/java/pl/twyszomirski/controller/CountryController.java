package pl.twyszomirski.controller;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import pl.twyszomirski.domain.Country;
import pl.twyszomirski.service.CountryService;

import java.util.List;

/**
 * Created by Tomasz
 */
@RestController
public class CountryController {

    @Autowired
    private CountryService countryService;

    @RequestMapping(value = "/countries", method = RequestMethod.GET)
    Iterable<Country> findAllCountries() {
        return countryService.findAll();
    }


}
