package com.gcteam.yamblz.homework.domain.object;

import java.io.Serializable;

/**
 * Created by turist on 16.07.2017.
 */

public class WeatherData implements Serializable {
    private int weatherId;
    private String description;

    private double dayTemp;
    private double minTemp;
    private double maxTemp;
    private double pressure; // hPa
    private int humidity; // %

    private double windSpeed;
    private double windDeg;

    private long date;

    public WeatherData(int weatherId,
                       String description,
                       double dayTemp,
                       double minTemp,
                       double maxTemp,
                       double pressure,
                       int humidity,
                       double windSpeed,
                       double windDeg,
                       long date) {
        this.weatherId = weatherId;
        this.description = description;
        this.dayTemp = dayTemp;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.pressure = pressure;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.windDeg = windDeg;
        this.date = date;
    }

    public int getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(int weatherId) {
        this.weatherId = weatherId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getDayTemp() {
        return dayTemp;
    }

    public void setDayTemp(double dayTemp) {
        this.dayTemp = dayTemp;
    }

    public double getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(double minTemp) {
        this.minTemp = minTemp;
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(double maxTemp) {
        this.maxTemp = maxTemp;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public double getWindDeg() {
        return windDeg;
    }

    public void setWindDeg(double windDeg) {
        this.windDeg = windDeg;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WeatherData that = (WeatherData) o;

        if (weatherId != that.weatherId) return false;
        if (Double.compare(that.dayTemp, dayTemp) != 0) return false;
        if (Double.compare(that.minTemp, minTemp) != 0) return false;
        if (Double.compare(that.maxTemp, maxTemp) != 0) return false;
        if (Double.compare(that.pressure, pressure) != 0) return false;
        if (humidity != that.humidity) return false;
        if (Double.compare(that.windSpeed, windSpeed) != 0) return false;
        if (Double.compare(that.windDeg, windDeg) != 0) return false;
        if (date != that.date) return false;
        return description != null ? description.equals(that.description) : that.description == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = weatherId;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        temp = Double.doubleToLongBits(dayTemp);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(minTemp);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(maxTemp);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(pressure);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + humidity;
        temp = Double.doubleToLongBits(windSpeed);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(windDeg);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (int) (date ^ (date >>> 32));
        return result;
    }
}