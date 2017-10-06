package com.gcteam.yamblz.homework.data.mapper;

import com.gcteam.yamblz.homework.data.api.dto.weather.current.WeatherResponse;
import com.gcteam.yamblz.homework.domain.object.WeatherData;

import io.reactivex.annotations.NonNull;

/**
 * Created by turist on 16.07.2017.
 */

public class WeatherResponseMapper {

    @NonNull
    public static WeatherData toWeatherData(@NonNull WeatherResponse weatherResponse) {

        return new WeatherData(
                weatherResponse.getWeather().get(0).getId(),
                weatherResponse.getWeather().get(0).getDescription(),
                weatherResponse.getMain().getTemp(),
                weatherResponse.getMain().getTempMin(),
                weatherResponse.getMain().getTempMax(),
                weatherResponse.getMain().getPressure(),
                weatherResponse.getMain().getHumidity(),
                weatherResponse.getWind().getSpeed(),
                weatherResponse.getWind().getDeg(),
                weatherResponse.getDt()
        );
    }
}
