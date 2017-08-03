package com.gcteam.yamblz.homework.weather;

import com.gcteam.yamblz.homework.R;
import com.gcteam.yamblz.homework.data.WeatherData;
import com.gcteam.yamblz.homework.data.local.WeatherStorage;
import com.gcteam.yamblz.homework.data.network.WeatherService;
import com.gcteam.yamblz.homework.data.WeatherRepository;
import com.gcteam.yamblz.homework.presentation.view.weather.WeatherView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Calendar;
import java.util.Locale;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.functions.Consumer;

import static org.mockito.Mockito.*;

/**
 * Created by Kim Michael on 26.07.17
 */
public class WeatherRepositoryTest {

    private WeatherRepository weatherRepository;
    @Mock
    private WeatherView weatherView;
    @Mock
    private WeatherStorage weatherStorage;
    @Mock
    private WeatherService weatherService;
    @Mock
    private Consumer<WeatherData> consumer;

    WeatherData testWeather;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        testWeather = new WeatherData(
                "", "", "desc", 100, 20d, 10, 12f, R.string.wind_nw, Calendar.getInstance());

        when(weatherStorage.lastWeather()).thenReturn(Observable.just(testWeather));

        when(weatherService.currentWeather(Locale.getDefault().getLanguage()))
                .thenReturn(Single.just(testWeather));

        when(weatherStorage.updateLastWeather()).thenReturn(consumer);

        weatherRepository = new WeatherRepository(
                weatherStorage,
                weatherService
        );
    }

}
