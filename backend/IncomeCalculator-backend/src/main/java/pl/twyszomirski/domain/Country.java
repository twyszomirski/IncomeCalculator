package pl.twyszomirski.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Tomasz
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
    private Float taxRate;

    @NotNull
    private Long additionalCost;


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

    public Float getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Float taxRate) {
        this.taxRate = taxRate;
    }

    public Long getAdditionalCost() {
        return additionalCost;
    }

    public void setAdditionalCost(Long additionalCost) {
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
