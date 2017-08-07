package com.gcteam.yamblz.homework.weather;

import com.gcteam.yamblz.homework.data.local.weather.WeatherStorage;
import com.gcteam.yamblz.homework.data.network.weather.WeatherService;
import com.gcteam.yamblz.homework.data.repository.weather.WeatherRepository;
import com.gcteam.yamblz.homework.domain.object.WeatherData;
import com.gcteam.yamblz.homework.presentation.view.weather.WeatherView;

import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Locale;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.functions.Consumer;

import static org.mockito.Mockito.when;

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
                100, "desc", 36.6d, 20.0d, 40.0d, 200.0d, 20, 5.5, 300);

        when(weatherStorage.lastWeather()).thenReturn(Observable.just(testWeather));

        when(weatherService.getCurrentWeather(Locale.getDefault().getLanguage()))
                .thenReturn(Single.just(testWeather));

        when(weatherStorage.updateLastWeather()).thenReturn(consumer);

        weatherRepository = new WeatherRepository(
                weatherStorage,
                weatherService
        );
    }

}
