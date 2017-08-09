package com.gcteam.yamblz.homework.data.object;

import android.arch.persistence.room.Entity;

/**
 * Created by Kim Michael on 09.08.17
 */
@Entity(primaryKeys = {"lat", "lng"})
public class StoredFullWeatherReport {
    private final double lat;
    private final double lng;
    private final String forecastDataJson;
    private final String weatherDataJson;

    public StoredFullWeatherReport(double lat, double lng, String forecastDataJson, String weatherDataJson) {
        this.lat = lat;
        this.lng = lng;
        this.forecastDataJson = forecastDataJson;
        this.weatherDataJson = weatherDataJson;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public String getForecastDataJson() {
        return forecastDataJson;
    }

    public String getWeatherDataJson() {
        return weatherDataJson;
    }
}
