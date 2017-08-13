package com.gcteam.yamblz.homework.data.network.weather;

import com.gcteam.yamblz.homework.domain.object.ForecastData;
import com.gcteam.yamblz.homework.domain.object.WeatherData;

import io.reactivex.Single;

/**
 * Created by Kim Michael on 13.08.17
 */
public interface WeatherService {

    Single<WeatherData> getCurrentWeather(double lat, double lng, String lang);

    Single<ForecastData> getForecast(double lat, double lng, String lang);
}
