package com.gcteam.yamblz.homework.data.network.weather;

import com.gcteam.yamblz.homework.data.api.OpenWeatherMapApi;
import com.gcteam.yamblz.homework.data.api.dto.weather.current.WeatherResponse;
import com.gcteam.yamblz.homework.data.mapper.WeatherResponseMapper;
import com.gcteam.yamblz.homework.domain.object.WeatherData;
import com.gcteam.yamblz.homework.utils.PreferencesManager;
import com.google.gson.Gson;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.Single;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * Created by Kim Michael on 27.07.17
 */
public class OpenWeatherServiceTest {

    WeatherService weatherService;
    WeatherResponseMapper weatherResponseMapper;
    @Mock
    PreferencesManager preferencesManager;
    @Mock
    OpenWeatherMapApi api;
    WeatherResponse testWeatherResponse;
    WeatherData testWeatherMapped;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);


        weatherResponseMapper = new WeatherResponseMapper();
        testWeatherResponse = new Gson().fromJson("{\"coord\":{\"lon\":30.52,\"lat\":50.45},\"weather\":[{\"id\":803,\"main\":\"Clouds\",\"description\":\"broken clouds\",\"icon\":\"04d\"}],\"base\":\"stations\",\"main\":{\"temp\":24.34,\"pressure\":1004,\"humidity\":78,\"temp_min\":23,\"temp_max\":26},\"visibility\":10000,\"wind\":{\"speed\":6,\"deg\":360},\"clouds\":{\"all\":75},\"dt\":1501250400,\"sys\":{\"type\":1,\"id\":7358,\"message\":0.0041,\"country\":\"UA\",\"sunrise\":1501208450,\"sunset\":1501264023},\"id\":696050,\"name\":\"Pushcha-Voditsa\",\"cod\":200}", WeatherResponse.class);
        testWeatherMapped = WeatherResponseMapper.toWeatherData(testWeatherResponse);

        when(api.weatherByLatLng(OpenWeatherMapApi.API_KEY, 20d, 20d, "metric", "ru")).thenReturn(Single.just(testWeatherResponse));

        double lat = 10d;
        double lng = 10d;
        when(preferencesManager.getLat()).thenReturn(lat);
        when(preferencesManager.getLng()).thenReturn(lng);

        weatherService = new OpenWeatherService(api);
    }

    @Test
    public void getCurrentWeatherFromAPI() {
        WeatherData weatherData = weatherService.getCurrentWeather(20d, 20d, "ru").blockingGet();
        assertTrue(weatherData.equals(testWeatherMapped));
    }

}
