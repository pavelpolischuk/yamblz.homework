package com.gcteam.yamblz.homework.data;

import android.support.annotation.StringRes;

import com.gcteam.yamblz.homework.R;
import com.gcteam.yamblz.homework.data.api.dto.weather.current.WeatherResponse;
import com.gcteam.yamblz.homework.domain.object.WeatherData;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by turist on 16.07.2017.
 */

public class WeatherResponseMapper implements Function<WeatherResponse, WeatherData> {

    @StringRes
    public static int windDirection(double directionDegree) {

        if (directionDegree < 158) {
            if (directionDegree < 68) {
                if (directionDegree < 23) {
                    return R.string.wind_n;
                }
                return R.string.wind_no;
            }

            if (directionDegree < 113) {
                return R.string.wind_o;
            }
            return R.string.wind_so;
        }

        if (directionDegree < 248) {
            if (directionDegree < 203) {
                return R.string.wind_s;
            }

            return R.string.wind_sw;
        }

        if (directionDegree < 338) {
            if (directionDegree < 293) {
                return R.string.wind_w;
            }

            return R.string.wind_nw;
        }

        return R.string.wind_n;
    }

    @Override
    public WeatherData apply(@NonNull WeatherResponse weatherResponse) throws Exception {

        return new WeatherData(
                weatherResponse.getWeather().get(0).getId(),
                weatherResponse.getWeather().get(0).getDescription(),
                weatherResponse.getMain().getTemp(),
                weatherResponse.getMain().getTempMin(),
                weatherResponse.getMain().getTempMax(),
                weatherResponse.getMain().getPressure(),
                weatherResponse.getMain().getHumidity(),
                weatherResponse.getWind().getSpeed(),
                weatherResponse.getWind().getDeg()
        );
    }
}
