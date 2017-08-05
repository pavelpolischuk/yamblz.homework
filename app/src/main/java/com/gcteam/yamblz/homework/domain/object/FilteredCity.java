package com.gcteam.yamblz.homework.domain.object;

/**
 * Created by Kim Michael on 03.08.17
 */
public class FilteredCity {
    private final String cityName;
    private final String countryName;
    private final String placeId;

    public FilteredCity(String cityName, String countryName, String placeId) {
        this.cityName = cityName;
        this.countryName = countryName;
        this.placeId = placeId;
    }

    public String getCityName() {
        return cityName;
    }

    public String getCountryName() {
        return countryName;
    }
}
