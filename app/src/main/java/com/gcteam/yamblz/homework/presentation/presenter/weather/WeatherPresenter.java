package com.gcteam.yamblz.homework.presentation.presenter.weather;

import com.gcteam.yamblz.homework.domain.interactor.weather.WeatherInteractor;
import com.gcteam.yamblz.homework.presentation.BasePresenter;
import com.gcteam.yamblz.homework.presentation.view.weather.WeatherView;

import javax.inject.Inject;

/**
 * Created by Kim Michael on 02.08.17
 */
public class WeatherPresenter extends BasePresenter<WeatherView> {

    WeatherInteractor weatherInteractor;

    @Inject
    public WeatherPresenter(WeatherInteractor weatherInteractor) {
        this.weatherInteractor = weatherInteractor;
    }

    public void startRefresh() {
        if (getView() != null) {
            getView().showLoadingStarted();
        }
        weatherInteractor.getWeather().subscribe(weatherData -> {
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
