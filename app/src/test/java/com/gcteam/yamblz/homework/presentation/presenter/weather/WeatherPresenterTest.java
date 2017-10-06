package com.gcteam.yamblz.homework.presentation.presenter.weather;

import com.gcteam.yamblz.homework.domain.interactor.weather.WeatherInteractor;
import com.gcteam.yamblz.homework.domain.object.FullWeatherReport;
import com.gcteam.yamblz.homework.presentation.view.weather.WeatherView;
import com.gcteam.yamblz.homework.utils.PreferencesManager;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.Single;
import io.reactivex.schedulers.TestScheduler;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Kim Michael on 11.08.17
 */
public class WeatherPresenterTest {

    WeatherPresenter weatherPresenter;
    @Mock
    WeatherInteractor weatherInteractor;
    @Mock
    PreferencesManager preferencesManager;
    @Mock
    WeatherView weatherView;

    private TestScheduler testScheduler;

    private String chosenCity;
    private double lat;
    private double lng;
    private FullWeatherReport fullWeatherReport;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        testScheduler = new TestScheduler();
        chosenCity = "Moscow";
        weatherPresenter = new WeatherPresenter(weatherInteractor);
        fullWeatherReport = mock(FullWeatherReport.class);
        when(preferencesManager.getChosenCity()).thenReturn(chosenCity);
        when(preferencesManager.getLat()).thenReturn(lat);
        when(preferencesManager.getLng()).thenReturn(lng);
    }

    @Test
    public void ifNoChosenCity_showEmptyView() {
        when(preferencesManager.getChosenCity()).thenReturn(PreferencesManager.NO_CHOSEN_CITY);
        weatherPresenter.onAttach(weatherView);
        weatherPresenter.refreshForecast(preferencesManager, false, true);
        verify(weatherView).showEmptyView();
    }

    @Test
    public void showFullWeatherForChosenCity() {
        weatherPresenter.onAttach(weatherView);
        when(weatherInteractor.getWeather(lat, lng, false))
                .thenReturn(Single.just(fullWeatherReport));

        weatherPresenter.refreshForecast(preferencesManager, false, true);

        verify(weatherInteractor).getWeather(lat, lng, false);
        verify(weatherView).showFullWeather(fullWeatherReport);
    }

    @Test
    public void ifCantGetWeather_showError() {
        weatherPresenter.onAttach(weatherView);
        when(weatherInteractor.getWeather(lat, lng, false))
                .thenReturn(Single.error(new Exception("No Internet")));

        weatherPresenter.refreshForecast(preferencesManager, false, true);

        verify(weatherView).showErrorMessage();
    }
}
