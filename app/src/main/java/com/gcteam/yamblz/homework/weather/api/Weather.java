package com.gcteam.yamblz.homework.weather.api;

import android.support.annotation.StringRes;

import java.io.Serializable;

/**
 * Created by turist on 16.07.2017.
 */

public class Weather implements Serializable {
    private String iconUri;
    private String condition;
    private String description;

    private float temperature;
    private int pressure; // hPa
    private int humidity; // %

    private float windSpeed;

    @StringRes
    private int windFormat;

    public Weather(String iconUri, String condition, String description,
                   float temperature, int pressure, int humidity,
                   float windSpeed, @StringRes int windFormat) {
        this.iconUri = iconUri;
        this.condition = condition;
        this.description = description;
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.windFormat = windFormat;
    }

    public String getIconUri() {
        return iconUri;
    }

    public String getCondition() {
        return condition;
    }

    public String getDescription() {
        return description;
    }

    public float getTemperature() {
        return temperature;
    }

    public int getPressure() {
        return pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public float getWindSpeed() {
        return windSpeed;
    }

    @StringRes
    public int getWindFormat() {
        return windFormat;
    }
}