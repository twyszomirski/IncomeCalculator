package pl.twyszomirski.service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Tomasz
 * Class used for pl.twyszomirski.service.ExchangeRateService.getCurrentExchangeRate.
 * Main purpose is to enable caching in the service.
 */
public class ExchangeRateToken {

    /**
     * Date format that singles out only days, months and year in the date
     */
    private static final String DATE_IDENTIFIER_FORMAT="ddMMyyyy";

    private String formCurrency;
    private String toCurrency;
    private String date;

    public ExchangeRateToken(String formCurrency, String toCurrency, Date date) {
        this.formCurrency = formCurrency;
        this.toCurrency = toCurrency;
        this.date = new SimpleDateFormat(DATE_IDENTIFIER_FORMAT).format(date);
    }

    public String getFormCurrency() {
        return formCurrency;
    }

    public void setFormCurrency(String formCurrency) {
        this.formCurrency = formCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExchangeRateToken that = (ExchangeRateToken) o;

        if (!formCurrency.equals(that.formCurrency)) return false;
        if (!toCurrency.equals(that.toCurrency)) return false;
        return date.equals(that.date);
    }

    @Override
    public int hashCode() {
        int result = formCurrency.hashCode();
        result = 31 * result + toCurrency.hashCode();
        result = 31 * result + date.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ExchangeRateToken{" +
                "formCurrency='" + formCurrency + '\'' +
                ", toCurrency='" + toCurrency + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
