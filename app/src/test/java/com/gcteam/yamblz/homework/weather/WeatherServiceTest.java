package com.gcteam.yamblz.homework.weather;

import com.gcteam.yamblz.homework.settings.PreferencesManager;
import com.gcteam.yamblz.homework.weather.api.OpenWeatherMapApi;
import com.gcteam.yamblz.homework.weather.api.WeatherMapper;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        weatherMapper = new WeatherMapper();
        weatherService = new WeatherService(preferencesManager, weatherMapper, api);
    }



}
