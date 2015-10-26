package pl.twyszomirski.yahoo.finance.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Tomasz
 * Dto for Yahoo Finance API
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultsDto {

    private RateDto rate;

    public RateDto getRate() {
        return rate;
    }

    public void setRate(RateDto rate) {
        this.rate = rate;
    }
}
