package pl.twyszomirski.dto;

import java.math.BigDecimal;

/**
 * Created by Tomasz
 * The Dto for income calculation responses
 */
public class IncomeCalculationResponseDto {

    BigDecimal monthlyRate;
    BigDecimal monthlyTax;
    BigDecimal additionalCost;

    public BigDecimal getMonthlyRate() {
        return monthlyRate;
    }

    public void setMonthlyRate(BigDecimal monthlyRate) {
        this.monthlyRate = monthlyRate;
    }

    public BigDecimal getMonthlyTax() {
        return monthlyTax;
    }

    public void setMonthlyTax(BigDecimal monthlyTax) {
        this.monthlyTax = monthlyTax;
    }

    public BigDecimal getAdditionalCost() {
        return additionalCost;
    }

    public void setAdditionalCost(BigDecimal additionalCost) {
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
