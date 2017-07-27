package com.gcteam.yamblz.homework.weather;

import com.gcteam.yamblz.homework.R;
import com.gcteam.yamblz.homework.weather.api.Weather;

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
public class WeatherLoadingInteractorTest {

    private WeatherLoadingInteractor weatherLoadingInteractor;
    @Mock
    private WeatherLoadingView weatherLoadingView;
    @Mock
    private WeatherStorage weatherStorage;
    @Mock
    private WeatherService weatherService;
    @Mock
    private Consumer<Weather> consumer;

    Weather testWeather;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        testWeather = new Weather(
                "", "", "desc", 100, 20d, 10, 12f, R.string.wind_nw, Calendar.getInstance());

        when(weatherStorage.lastWeather()).thenReturn(Observable.just(testWeather));

        when(weatherService.currentWeather(Locale.getDefault().getLanguage()))
                .thenReturn(Single.just(testWeather));

        when(weatherStorage.updateLastWeather()).thenReturn(consumer);

        weatherLoadingInteractor = new WeatherLoadingInteractor(
                weatherStorage,
                weatherService
        );
    }

    @Test
    public void startLoadingWhenBound() {
        weatherLoadingInteractor.bind(weatherLoadingView);
        verify(weatherLoadingView).loadingStart();
    }

    @Test
    public void showSpinnerOnBinding() throws Exception {
        weatherLoadingInteractor.bind(weatherLoadingView);
        verify(weatherLoadingView).loadingStart();
    }


}
