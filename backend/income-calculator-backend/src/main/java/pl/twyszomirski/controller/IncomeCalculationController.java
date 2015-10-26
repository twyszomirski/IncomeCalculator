package pl.twyszomirski.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.twyszomirski.domain.Country;
import pl.twyszomirski.dto.IncomeCalculationResponseDto;
import pl.twyszomirski.service.IncomeCalculationService;
import pl.twyszomirski.service.NoExchangeRateException;

import java.math.BigDecimal;

/**
 * Created by Tomasz
 * Controller for income calculations
 */
@RestController
public class IncomeCalculationController {

    @Autowired
    private IncomeCalculationService incomeCalculationService;

    @RequestMapping(value = "/income-calculations", method = RequestMethod.GET)
    IncomeCalculationResponseDto calculateIncome(@RequestParam("daily_rate") BigDecimal dailyRate, @RequestParam("country_code")  String countryCode) throws NoExchangeRateException {
        return incomeCalculationService.calculateIncome(dailyRate, countryCode);
    }
}
