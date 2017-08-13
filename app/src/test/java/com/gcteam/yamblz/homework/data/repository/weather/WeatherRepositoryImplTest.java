package com.gcteam.yamblz.homework.data.repository.weather;

import com.gcteam.yamblz.homework.data.local.weather.WeatherStorage;
import com.gcteam.yamblz.homework.data.network.weather.WeatherService;
import com.gcteam.yamblz.homework.domain.object.ForecastData;
import com.gcteam.yamblz.homework.domain.object.FullWeatherReport;
import com.gcteam.yamblz.homework.domain.object.WeatherData;
import com.gcteam.yamblz.homework.utils.PreferencesManager;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.Single;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Kim Michael on 11.08.17
 */
public class WeatherRepositoryImplTest {

    WeatherRepository weatherRepositoryImpl;
    @Mock
    WeatherStorage weatherStorage;
    @Mock
    WeatherService weatherService;
    @Mock
    PreferencesManager preferencesManager;

    @Mock
    FullWeatherReport fullWeatherReport;
    @Mock
    WeatherData weatherData;
    @Mock
    ForecastData forecastData;

    private double lat = 15.0d;
    private double lng = 10.0d;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        weatherRepositoryImpl = new WeatherRepositoryImpl(weatherStorage, weatherService);
    }

    @Test
    public void getFullWeatherFromStorage() {
        when(weatherStorage.getFullWeatherReport(lat, lng)).thenReturn(Single.just(fullWeatherReport));
        when(weatherService.getCurrentWeather(eq(lat), eq(lng), anyString()))
                .thenReturn(Single.error(new Exception("No internet")));
        when(weatherService.getForecast(eq(lat), eq(lng), anyString()))
                .thenReturn(Single.error(new Exception("No internet")));

        FullWeatherReport fetchedReport = weatherRepositoryImpl.getFullWeatherReport(lat, lng).blockingGet();

        assertThat(fetchedReport).isEqualTo(fullWeatherReport);
        verify(weatherStorage).getFullWeatherReport(lat, lng);

    }

    @Test
    public void onStorageError_getWeatherFromService() {
        when(weatherService.getCurrentWeather(eq(lat), eq(lng), anyString()))
                .thenReturn(Single.just(weatherData));

    }

}
