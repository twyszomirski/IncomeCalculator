package pl.twyszomirski.dto;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created by Tomasz
 */
public class IncomeCalculationRequestDto {

    @NotNull
    BigDecimal dailyRate;
    @NotNull
    String countryCode;

    public BigDecimal getDailyRate() {
        return dailyRate;
    }

    public void setDailyRate(BigDecimal dailyRate) {
        this.dailyRate = dailyRate;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @Override
    public String toString() {
        return "IncomeCalculationRequestDto{" +
                "dailyRate=" + dailyRate +
                ", countryCode='" + countryCode + '\'' +
                '}';
    }
}
