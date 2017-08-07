package com.gcteam.yamblz.homework.domain.object;

import java.util.List;

/**
 * Created by Kim Michael on 06.08.17
 */
public class ForecastData {
    private String cityName;

    private List<WeatherData> forecast;

    public ForecastData(String cityName,
                        List<WeatherData> forecast) {
        this.cityName = cityName;
        this.forecast = forecast;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public List<WeatherData> getForecast() {
        return forecast;
    }

    public void setForecast(List<WeatherData> forecast) {
        this.forecast = forecast;
    }
}
