package pl.twyszomirski.dto;

/**
 * Created by Tomasz
 */
public class IncomeCalculationResponseDto {

    Float monthlyRate;
    Float monthlyTax;
    Float additionalCost;

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

    public Float getAdditionalCost() {
        return additionalCost;
    }

    public void setAdditionalCost(Float additionalCost) {
        this.additionalCost = additionalCost;
    }

    @Override
    public String toString() {
        return "IncomeCalculationResponseDto{" +
                "monthlyRate=" + monthlyRate +
                ", monthlyTax=" + monthlyTax +
                ", additionalCost=" + additionalCost +
                '}';
    }
}
