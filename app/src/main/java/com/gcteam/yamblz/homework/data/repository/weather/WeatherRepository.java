package com.gcteam.yamblz.homework.data.repository.weather;

import com.gcteam.yamblz.homework.data.mapper.ForecastResponseMapper;
import com.gcteam.yamblz.homework.data.local.weather.WeatherStorage;
import com.gcteam.yamblz.homework.data.network.weather.WeatherService;
import com.gcteam.yamblz.homework.domain.object.ForecastData;
import com.gcteam.yamblz.homework.domain.object.FullWeatherReport;
import com.gcteam.yamblz.homework.domain.object.WeatherData;

import java.util.Locale;

import javax.inject.Inject;

import io.reactivex.Single;
import timber.log.Timber;

/**
 * Created by turist on 16.07.2017.
 */

public class WeatherRepository {

    private final WeatherStorage weatherStorage;
    private final WeatherService weatherService;
    private final ForecastResponseMapper forecastResponseMapper;

    @Inject
    public WeatherRepository(WeatherStorage weatherStorage,
                             WeatherService weatherService,
                             ForecastResponseMapper forecastResponseMapper) {
        this.weatherStorage = weatherStorage;
        this.weatherService = weatherService;
        this.forecastResponseMapper = forecastResponseMapper;
    }

    public Single<WeatherData> getWeather(double lat, double lng) {
        return weatherStorage.getCurrentWeather(lat, lng).onErrorResumeNext(
                weatherService.getCurrentWeather(lat, lng, Locale.getDefault().getLanguage()));
    }

    public Single<ForecastData> getForecast(double lat, double lng) {
        return weatherStorage.getForecast(lat, lng).onErrorResumeNext(
                weatherService
                        .getForecast(lat, lng, Locale.getDefault().getLanguage())
                        .map(forecastResponseMapper)
        );
    }

    public void saveWeather(FullWeatherReport fullWeatherReport) {
        Timber.d("Full weather report is being saved for %1.0f lat and %1.0f lng",
                fullWeatherReport.getLat(), fullWeatherReport.getLng());
        weatherStorage.saveWeather(fullWeatherReport);
    }
}