package com.gcteam.yamblz.homework.weather;

import com.gcteam.yamblz.homework.data.local.weather.WeatherStorage;
import com.gcteam.yamblz.homework.domain.object.WeatherData;
import com.gcteam.yamblz.homework.utils.PreferencesManager;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.functions.Consumer;

import static junit.framework.Assert.fail;
import static org.mockito.Mockito.verify;

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
        weatherData = new WeatherData(
                100, "desc", 36.6d, 20.0d, 40.0d, 200.0d, 20, 5.5, 300);
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
            fail("Consumer failed to accept weatherResponse data");
        }
    }
}
