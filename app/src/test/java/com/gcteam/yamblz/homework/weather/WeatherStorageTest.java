package com.gcteam.yamblz.homework.weather;

import com.gcteam.yamblz.homework.R;
import com.gcteam.yamblz.homework.settings.PreferencesManager;
import com.gcteam.yamblz.homework.weather.api.WeatherData;
import com.gcteam.yamblz.homework.weather.api.dto.Weather;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Calendar;

import io.reactivex.functions.Consumer;

import static junit.framework.Assert.fail;
import static org.mockito.Mockito.*;

/**
 * Created by Kim Michael on 27.07.17
 */
public class WeatherStorageTest {

    WeatherStorage weatherStorage;
    @Mock
    PreferencesManager preferencesManager;
    WeatherData weatherData;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        weatherData = new WeatherData("", "", "", 20.0f, 100d, 50, 1.0f, R.string.wind_n, Calendar.getInstance());
        weatherStorage = new WeatherStorage(preferencesManager);
    }

    @Test
    public void storageCanSaveToPreferences() {
        weatherStorage.save(weatherData);
        verify(preferencesManager).putCurrentWeather(weatherData);
    }

    @Test
    public void storageCanLoadFromPreferences() {
        weatherStorage.load();
        verify(preferencesManager).loadCachedWeather();
    }

    @Test
    public void lastWeatherCanBeEmitted() {
        Consumer<WeatherData> consumer = weatherStorage.updateLastWeather();
        try {
            consumer.accept(weatherData);
        } catch (Exception e) {
            fail("Consumer failed to accept weather data");
        }
    }
}
