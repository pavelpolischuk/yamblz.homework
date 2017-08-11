package com.gcteam.yamblz.homework.domain.interactor.weather;

import com.gcteam.yamblz.homework.data.repository.weather.WeatherRepository;
import com.gcteam.yamblz.homework.domain.object.ForecastData;
import com.gcteam.yamblz.homework.domain.object.FullWeatherReport;
import com.gcteam.yamblz.homework.domain.object.WeatherData;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.Single;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.TestScheduler;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Kim Michael on 11.08.17
 */
public class WeatherInteractorTest {

    final double lat = 10;
    final double lng = 15;

    WeatherInteractor weatherInteractor;
    @Mock
    WeatherRepository weatherRepository;
    @Mock
    ForecastData forecastData;
    @Mock
    WeatherData weatherData;
    TestScheduler testScheduler;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        testScheduler = new TestScheduler();
        weatherInteractor = new WeatherInteractor(
                weatherRepository,
                testScheduler,
                testScheduler);
        when(weatherRepository.getForecast(lat, lng)).thenReturn(Single.just(forecastData));
        when(weatherRepository.getWeather(lat, lng)).thenReturn(Single.just(weatherData));
    }

    @Test
    public void getsWeatherFromRepository_savesToRepository() {

        TestObserver<FullWeatherReport> testObserver =
                weatherInteractor.getWeather(lat, lng, false).test();
        testScheduler.triggerActions();

        testObserver.assertNoErrors();
        verify(weatherRepository).getForecast(lat,lng);
        verify(weatherRepository).getWeather(lat,lng);
        verify(weatherRepository).saveWeather(any());
    }

    @Test
    public void cachesLastWeather() {
        TestObserver<FullWeatherReport> testObserver =
                weatherInteractor.getWeather(lat, lng, false).test();
        testScheduler.triggerActions();
        testObserver.assertNoErrors();

        TestObserver<FullWeatherReport> secondTestObserver =
                weatherInteractor.getWeather(lat, lng, false).test();
        testScheduler.triggerActions();
        secondTestObserver.assertNoErrors();

        verify(weatherRepository, times(1)).getForecast(lat, lng);
        verify(weatherRepository, times(1)).getWeather(lat, lng);
    }

    @Test
    public void onForceUpdate_doesntUseCache() {
        TestObserver<FullWeatherReport> testObserver =
                weatherInteractor.getWeather(lat, lng, false).test();
        testScheduler.triggerActions();
        testObserver.assertNoErrors();

        TestObserver<FullWeatherReport> secondTestObserver =
                weatherInteractor.getWeather(lat, lng, true).test();
        testScheduler.triggerActions();
        secondTestObserver.assertNoErrors();

        verify(weatherRepository, times(2)).getForecast(lat, lng);
        verify(weatherRepository, times(2)).getWeather(lat, lng);
    }
}
