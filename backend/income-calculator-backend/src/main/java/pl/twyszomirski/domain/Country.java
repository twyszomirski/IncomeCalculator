package pl.twyszomirski.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created by Tomasz
 * Entity class representing country
 */
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"countryCode"}))
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private long id;

    @NotNull
    private String name;

    @NotNull
    private String countryCode;

    @NotNull
    private String currencyCode;

    @NotNull
    private BigDecimal taxRate;

    @NotNull
    private BigDecimal additionalCost;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public BigDecimal getAdditionalCost() {
        return additionalCost;
    }

    public void setAdditionalCost(BigDecimal additionalCost) {
        this.additionalCost = additionalCost;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Country)) return false;

        Country country = (Country) o;

        return !(getCountryCode() != null ? !getCountryCode().equals(country.getCountryCode()) : country.getCountryCode() != null);

    }

    @Override
    public int hashCode() {
        return getCountryCode() != null ? getCountryCode().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", currencyCode='" + currencyCode + '\'' +
                ", taxRate=" + taxRate +
                ", additionalCost=" + additionalCost +
                '}';
    }
}
