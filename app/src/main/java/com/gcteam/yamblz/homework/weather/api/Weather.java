package com.gcteam.yamblz.homework.weather.api;

import android.support.annotation.StringRes;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by turist on 16.07.2017.
 */

public class Weather implements Serializable {
    private String iconUri;
    private String condition;
    private String description;

    private float temperature;
    private double pressure; // hPa
    private int humidity; // %

    private float windSpeed;
    private long updated;

    @StringRes
    private int windFormat;

    public Weather(String iconUri, String condition, String description,
                   float temperature, double pressure, int humidity,
                   float windSpeed, @StringRes int windFormat, Calendar updated) {
        this.iconUri = iconUri;
        this.condition = condition;
        this.description = description;
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.windFormat = windFormat;
        this.updated = updated.getTimeInMillis();
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

    public double getPressure() {
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

    public Calendar getUpdatingTime() {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(updated);
        return c;
    }
}