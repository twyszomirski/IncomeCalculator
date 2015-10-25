package pl.twyszomirski.dto;

import javax.validation.constraints.NotNull;

/**
 * Created by Tomasz
 */
public class IncomeCalculationRequestDto {

    @NotNull
    Float dailyRate;
    @NotNull
    String countryCode;

    public Float getDailyRate() {
        return dailyRate;
    }

    public void setDailyRate(Float dailyRate) {
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
