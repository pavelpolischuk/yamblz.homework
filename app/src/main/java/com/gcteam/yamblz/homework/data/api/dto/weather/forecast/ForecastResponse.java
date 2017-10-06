package com.gcteam.yamblz.homework.data.api.dto.weather.forecast;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ForecastResponse {

    @SerializedName("city")
    private City city;
    @SerializedName("list")
    private List<Description> description = null;

    public City getCity() {
        return city;
    }

    public List<Description> getDescriptions() {
        return description;
    }

}
