package pl.twyszomirski.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.twyszomirski.domain.Country;
import pl.twyszomirski.dto.IncomeCalculationRequestDto;
import pl.twyszomirski.dto.IncomeCalculationResponseDto;
import pl.twyszomirski.service.IncomeCalculationService;
import pl.twyszomirski.service.NoExchangeRateException;

/**
 * Created by Tomasz
 * Controller for income calculations
 */
@RestController
public class IncomeCalculationController {

    @Autowired
    private IncomeCalculationService incomeCalculationService;

    @RequestMapping(value = "/incomeCalculations", method = RequestMethod.POST)
    IncomeCalculationResponseDto calculateIncome(@RequestBody @Validated IncomeCalculationRequestDto incomeCalculationDto) throws NoExchangeRateException {
        return incomeCalculationService.calculateIncome(incomeCalculationDto.getDailyRate(),incomeCalculationDto.getCountryCode());
    }
}
