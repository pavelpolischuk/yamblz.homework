package com.gcteam.yamblz.homework.data;

import com.gcteam.yamblz.homework.presentation.view.weather.WeatherLoadingView;
import com.gcteam.yamblz.homework.data.network.WeatherService;
import com.gcteam.yamblz.homework.data.local.WeatherStorage;

import java.util.Locale;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by turist on 16.07.2017.
 */

public class WeatherRepository {

    private Disposable subscription;
    private Consumer<Throwable> errorHandler;
    private WeatherStorage weatherStorage;
    private WeatherService weatherService;

    @Inject
    public WeatherRepository(WeatherStorage weatherStorage,
                             WeatherService weatherService) {
        this.weatherStorage = weatherStorage;
        this.weatherService = weatherService;
    }

    public void bind(final WeatherLoadingView view) {
        this.errorHandler = throwable -> view.loadingError();

        Observable<WeatherData> lastWeather = weatherStorage.lastWeather();
        WeatherData fromStorage = weatherStorage.load();
        if(fromStorage != null) {
            this.subscription = lastWeather.startWith(fromStorage).subscribe(weather -> view.loaded(weather));
            return;
        }

        this.subscription = lastWeather.subscribe(weather -> view.loaded(weather));

        startRefresh();
        view.loadingStart();
    }

    public void unbind() {
        if(subscription != null && !subscription.isDisposed()) {
            subscription.dispose();
            subscription = null;
        }

        this.errorHandler = throwable -> {
        };
    }

    public Disposable startRefresh() {
        Single<WeatherData> currentWeather = weatherService
                .currentWeather(Locale.getDefault().getLanguage());

        return currentWeather
                .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(weatherStorage.updateLastWeather(), errorHandler);
    }
}