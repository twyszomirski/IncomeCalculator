package pl.twyszomirski.yahoo.finance.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Tomasz
 * Dto for Yahoo Finance API
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangeRateDto {

    private QueryDto query;

    public QueryDto getQuery() {
        return query;
    }

    public void setQuery(QueryDto query) {
        this.query = query;
    }
}
