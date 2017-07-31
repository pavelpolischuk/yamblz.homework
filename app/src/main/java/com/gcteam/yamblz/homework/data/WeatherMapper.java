package com.gcteam.yamblz.homework.data;

import android.support.annotation.StringRes;

import com.gcteam.yamblz.homework.R;
import com.gcteam.yamblz.homework.data.api.dto.Weather;
import com.gcteam.yamblz.homework.data.api.dto.WeatherCondition;

import java.util.Calendar;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by turist on 16.07.2017.
 */

public class WeatherMapper implements Function<Weather, WeatherData> {

    @Override
    public WeatherData apply(@NonNull Weather w) throws Exception {

        WeatherCondition condition;
        if(w.conditions.isEmpty()) {
            condition = new WeatherCondition();
        } else {
            condition = w.conditions.get(0);
        }

        return new WeatherData(iconUrl(condition.iconId), condition.main, condition.description,
                w.main.temperature, w.main.pressure, w.main.humidity,
                w.wind.speed, windDirection(w.wind.directionDegree), Calendar.getInstance());
    }

    public static String iconUrl(String iconId) {
        return String.format("http://openweathermap.org/img/w/%s.png", iconId);
    }

    @StringRes
    public static int windDirection(double directionDegree) {

        if(directionDegree < 158) {
            if(directionDegree < 68) {
                if(directionDegree < 23) {
                    return R.string.wind_n;
                }
                return R.string.wind_no;
            }

            if(directionDegree < 113) {
                return R.string.wind_o;
            }
            return R.string.wind_so;
        }

        if(directionDegree < 248) {
            if(directionDegree < 203) {
                return R.string.wind_s;
            }

            return R.string.wind_sw;
        }

        if(directionDegree < 338) {
            if(directionDegree < 293) {
                return R.string.wind_w;
            }

            return R.string.wind_nw;
        }

        return R.string.wind_n;
    }
}
