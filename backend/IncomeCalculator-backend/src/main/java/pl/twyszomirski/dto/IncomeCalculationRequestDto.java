package pl.twyszomirski.dto;

/**
 * Created by Tomasz
 */
public class IncomeCalculationRequestDto {

    Float dailyRate;
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
