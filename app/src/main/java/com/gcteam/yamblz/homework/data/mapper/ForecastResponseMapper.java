package com.gcteam.yamblz.homework.data.mapper;

import com.gcteam.yamblz.homework.data.api.dto.weather.forecast.Description;
import com.gcteam.yamblz.homework.data.api.dto.weather.forecast.ForecastResponse;
import com.gcteam.yamblz.homework.domain.object.ForecastData;
import com.gcteam.yamblz.homework.domain.object.WeatherData;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.NonNull;

/**
 * Created by Kim Michael on 06.08.17
 */
public class ForecastResponseMapper {

    public static ForecastData toForecastData(@NonNull ForecastResponse forecastResponse) {
        List<WeatherData> forecast = new ArrayList<>();
        WeatherData weatherData;
        for (Description description : forecastResponse.getDescriptions()) {
            weatherData = new WeatherData(
                    description.getWeather().get(0).getId(),
                    description.getWeather().get(0).getDescription(),
                    description.getTemp().getDay(),
                    description.getTemp().getMin(),
                    description.getTemp().getMax(),
                    description.getPressure(),
                    description.getHumidity(),
                    description.getSpeed(),
                    description.getDeg(),
                    description.getDt()
            );
            forecast.add(weatherData);

        }
        return new ForecastData(
                forecastResponse.getCity().getName(),
                forecast);
    }
}
