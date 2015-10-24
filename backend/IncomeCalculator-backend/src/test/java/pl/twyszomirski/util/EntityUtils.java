package pl.twyszomirski.util;

import pl.twyszomirski.domain.Country;

/**
 * Created by Tomasz
 */
public class EntityUtils {

    public static Country buildCountry(String countryCode, String currencyCode, String name,
                                Float taxRate, Long additionalCost){
        Country country = new Country();
        country.setCountryCode(countryCode);
        country.setName(name);
        country.setCurrencyCode(currencyCode);
        country.setTaxRate(taxRate);
        country.setAdditionalCost(additionalCost);
        return country;
    }
}
