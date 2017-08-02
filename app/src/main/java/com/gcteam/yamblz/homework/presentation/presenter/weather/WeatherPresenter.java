package com.gcteam.yamblz.homework.presentation.presenter.weather;

import com.gcteam.yamblz.homework.data.WeatherData;
import com.gcteam.yamblz.homework.domain.interactor.weather.WeatherInteractor;
import com.gcteam.yamblz.homework.presentation.BasePresenter;
import com.gcteam.yamblz.homework.presentation.view.weather.WeatherView;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

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
        weatherInteractor.getWeather().subscribe(new SingleObserver<WeatherData>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                getView().showLoadingStarted();
            }

            @Override
            public void onSuccess(@NonNull WeatherData weatherData) {
                getView().showWeatherData(weatherData);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                getView().showErrorMessage();
            }
        });
    }
}
