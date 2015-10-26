package pl.twyszomirski.yahoo.finance.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Tomasz
 * Dto for Yahoo Finance API
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryDto {

    private ResultsDto results;

    public ResultsDto getResults() {
        return results;
    }

    public void setResults(ResultsDto results) {
        this.results = results;
    }
}
