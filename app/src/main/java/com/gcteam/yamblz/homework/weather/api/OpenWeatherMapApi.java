package com.gcteam.yamblz.homework.weather.api;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

import com.gcteam.yamblz.homework.weather.api.dto.Weather;

/**
 * Created by turist on 16.07.2017.
 */

public interface OpenWeatherMapApi {

    @GET("2.5/weather")
    Single<Weather> current(@Query("appid") String apiKey, @Query("id") int cityId,
                            @Query("units") String unitsFormat,
                            @Query("lang") String lang);
}