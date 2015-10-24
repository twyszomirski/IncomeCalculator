package pl.twyszomirski.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.twyszomirski.domain.Country;
import pl.twyszomirski.dto.IncomeCalculationRequestDto;
import pl.twyszomirski.dto.IncomeCalculationResponseDto;
import pl.twyszomirski.service.IncomeCalculationService;
import pl.twyszomirski.service.NoExchangeRateException;

/**
 * Created by Tomasz
 */
@RestController
public class IncomeCalculationController {

    @Autowired
    private IncomeCalculationService incomeCalculationService;

    @RequestMapping(value = "/incomeCalculations", method = RequestMethod.POST)
    IncomeCalculationResponseDto calculateIncome(@RequestBody IncomeCalculationRequestDto incomeCalculationDto) throws NoExchangeRateException {
        //TODO: add error translation here
        return incomeCalculationService.calculateIncome(incomeCalculationDto.getDailyRate(),incomeCalculationDto.getCountryCode());
    }
}
