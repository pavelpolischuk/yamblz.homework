package com.gcteam.yamblz.homework.data.repository.weather;

import android.support.annotation.NonNull;

import com.gcteam.yamblz.homework.domain.object.FullWeatherReport;

import io.reactivex.Single;

/**
 * Created by Kim Michael on 13.08.17
 */
public interface WeatherRepository {

    Single<FullWeatherReport> getFullWeatherReport(double lat, double lng);

    void saveWeather(@NonNull FullWeatherReport fullWeatherReport);
}
