package pl.twyszomirski.dto;

/**
 * Created by Tomasz
 */
public class IncomeCalculationDto {

    //TODO: separete this into two dtos

    Float dailyRate;
    String currencyCode;
    Float monthlyRate;
    Float monthlyTax;
    Long additionalCost;

    public Float getDailyRate() {
        return dailyRate;
    }

    public void setDailyRate(Float dailyRate) {
        this.dailyRate = dailyRate;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Float getMonthlyRate() {
        return monthlyRate;
    }

    public void setMonthlyRate(Float monthlyRate) {
        this.monthlyRate = monthlyRate;
    }

    public Float getMonthlyTax() {
        return monthlyTax;
    }

    public void setMonthlyTax(Float monthlyTax) {
        this.monthlyTax = monthlyTax;
    }

    public Long getAdditionalCost() {
        return additionalCost;
    }

    public void setAdditionalCost(Long additionalCost) {
        this.additionalCost = additionalCost;
    }
}
