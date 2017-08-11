package com.gcteam.yamblz.homework.data.api.dto.weather.forecast;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Description {

    @SerializedName("dt")
    private long dt;
    @SerializedName("temp")
    private Temp temp;
    @SerializedName("pressure")
    private double pressure;
    @SerializedName("humidity")
    private int humidity;
    @SerializedName("weather")
    private List<Weather> weather = null;
    @SerializedName("speed")
    private double speed;
    @SerializedName("deg")
    private double deg;

    public long getDt() {
        return dt;
    }

    public Temp getTemp() {
        return temp;
    }

    public double getPressure() {
        return pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public java.util.List<Weather> getWeather() {
        return weather;
    }

    public double getSpeed() {
        return speed;
    }

    public double getDeg() {
        return deg;
    }

}
