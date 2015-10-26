package pl.twyszomirski.yahoo.finance.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Tomasz
 * Dto for Yahoo Finance API
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RateDto {

    private String id;

    @JsonProperty("Rate")
    private String rate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "RateDto{" +
                "id='" + id + '\'' +
                ", rate='" + rate + '\'' +
                '}';
    }
}
