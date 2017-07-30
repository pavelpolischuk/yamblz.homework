package com.gcteam.yamblz.homework.weather;

import android.support.annotation.UiThread;

import com.gcteam.yamblz.homework.R;
import com.gcteam.yamblz.homework.settings.PreferencesManager;
import com.gcteam.yamblz.homework.weather.api.OpenWeatherMapApi;
import com.gcteam.yamblz.homework.weather.api.WeatherData;
import com.gcteam.yamblz.homework.weather.api.WeatherMapper;
import com.gcteam.yamblz.homework.weather.api.dto.Weather;
import com.google.gson.Gson;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Calendar;

import io.reactivex.Single;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by Kim Michael on 27.07.17
 */
public class WeatherServiceTest {

    WeatherService weatherService;
    WeatherMapper weatherMapper;
    @Mock
    PreferencesManager preferencesManager;
    @Mock
    OpenWeatherMapApi api;
    Weather testWeather;
    WeatherData testWeatherMapped;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);


        weatherMapper = new WeatherMapper();
        testWeather = new Gson().fromJson("{\"coord\":{\"lon\":30.52,\"lat\":50.45},\"weather\":[{\"id\":803,\"main\":\"Clouds\",\"description\":\"broken clouds\",\"icon\":\"04d\"}],\"base\":\"stations\",\"main\":{\"temp\":24.34,\"pressure\":1004,\"humidity\":78,\"temp_min\":23,\"temp_max\":26},\"visibility\":10000,\"wind\":{\"speed\":6,\"deg\":360},\"clouds\":{\"all\":75},\"dt\":1501250400,\"sys\":{\"type\":1,\"id\":7358,\"message\":0.0041,\"country\":\"UA\",\"sunrise\":1501208450,\"sunset\":1501264023},\"id\":696050,\"name\":\"Pushcha-Voditsa\",\"cod\":200}", Weather.class);
        testWeatherMapped = weatherMapper.apply(testWeather);

        when(api.weatherByLatLng(anyString(),
                anyString(),
                anyString(),
                anyString(),
                anyString())).thenReturn(Single.just(testWeather));

        double lat = 10d;
        double lng = 10d;
        when(preferencesManager.getLat()).thenReturn(lat);
        when(preferencesManager.getLng()).thenReturn(lng);

        weatherService = new WeatherService(preferencesManager, weatherMapper, api);
    }

    @Test
    public void getCurrentWeatherFromAPI() {
        WeatherData weatherData = weatherService.currentWeather("ru").blockingGet();
        assertTrue(weatherData.equals(testWeatherMapped));
    }

}
