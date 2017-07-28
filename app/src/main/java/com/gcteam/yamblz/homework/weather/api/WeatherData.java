package com.gcteam.yamblz.homework.weather.api;

import android.support.annotation.StringRes;

import com.gcteam.yamblz.homework.weather.api.dto.Weather;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by turist on 16.07.2017.
 */

public class WeatherData implements Serializable {
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

    public WeatherData(String iconUri, String condition, String description,
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WeatherData weather = (WeatherData) o;

        if (Float.compare(weather.temperature, temperature) != 0) return false;
        if (Double.compare(weather.pressure, pressure) != 0) return false;
        if (humidity != weather.humidity) return false;
        if (Float.compare(weather.windSpeed, windSpeed) != 0) return false;
        if (windFormat != weather.windFormat) return false;
        if (iconUri != null ? !iconUri.equals(weather.iconUri) : weather.iconUri != null)
            return false;
        if (condition != null ? !condition.equals(weather.condition) : weather.condition != null)
            return false;
        return description != null ? description.equals(weather.description) : weather.description == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = iconUri != null ? iconUri.hashCode() : 0;
        result = 31 * result + (condition != null ? condition.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (temperature != +0.0f ? Float.floatToIntBits(temperature) : 0);
        temp = Double.doubleToLongBits(pressure);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + humidity;
        result = 31 * result + (windSpeed != +0.0f ? Float.floatToIntBits(windSpeed) : 0);
        result = 31 * result + (int) (updated ^ (updated >>> 32));
        result = 31 * result + windFormat;
        return result;
    }

    public Calendar getUpdatingTime() {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(updated);
        return c;

    }
}