package com.gcteam.yamblz.homework.domain.object;

/**
 * Created by Kim Michael on 08.08.17
 */
public class FullWeatherReport {

    private final double lat;
    private final double lng;
    private final ForecastData forecastData;
    private final WeatherData weatherData;

    public FullWeatherReport(double lat, double lng, ForecastData forecastData, WeatherData weatherData) {
        this.forecastData = forecastData;
        this.weatherData = weatherData;
        this.lat = lat;
        this.lng = lng;
    }

    public ForecastData getForecastData() {
        return forecastData;
    }

    public WeatherData getWeatherData() {
        return weatherData;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }
}
