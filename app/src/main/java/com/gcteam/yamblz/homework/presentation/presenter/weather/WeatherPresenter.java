package com.gcteam.yamblz.homework.presentation.presenter.weather;

import com.gcteam.yamblz.homework.domain.interactor.weather.current.CurrentWeatherInteractor;
import com.gcteam.yamblz.homework.presentation.BasePresenter;
import com.gcteam.yamblz.homework.presentation.view.weather.WeatherView;

import javax.inject.Inject;

/**
 * Created by Kim Michael on 02.08.17
 */
public class WeatherPresenter extends BasePresenter<WeatherView> {

    CurrentWeatherInteractor currentWeatherInteractor;

    @Inject
    public WeatherPresenter(CurrentWeatherInteractor currentWeatherInteractor) {
        this.currentWeatherInteractor = currentWeatherInteractor;
    }

    public void startRefresh() {
        if (getView() != null) {
            getView().showLoadingStarted();
        }
        currentWeatherInteractor.getWeather().subscribe(weatherData -> {
            if (getView() != null) {
                getView().showWeatherData(weatherData);
            }
        }, throwable -> {
            if (getView() != null) {
                getView().showErrorMessage();
            }
        });
    }
}
