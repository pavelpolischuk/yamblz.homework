package com.gcteam.yamblz.homework.weather;

import android.content.SharedPreferences;
import android.support.v7.preference.PreferenceManager;

import com.gcteam.yamblz.homework.settings.PreferencesManager;
import com.gcteam.yamblz.homework.weather.api.Weather;
import com.google.android.gms.maps.model.LatLng;

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

public class WeatherLoadingInteractor {

    private Disposable subscription;
    private Consumer<Throwable> errorHandler;
    private WeatherStorage weatherStorage;
    private WeatherService weatherService;

    @Inject
    public WeatherLoadingInteractor(WeatherStorage weatherStorage,
                                    WeatherService weatherService) {
        this.weatherStorage = weatherStorage;
        this.weatherService = weatherService;
    }

    void bind(final WeatherLoadingView view) {
        this.errorHandler = new Consumer<Throwable>() {
            @Override
            public void accept(@NonNull Throwable throwable) throws Exception {
                view.loadingError();
            }
        };

        Observable<Weather> lastWeather = weatherStorage.lastWeather();
        Weather fromStorage = weatherStorage.load();
        if(fromStorage != null) {
            this.subscription = lastWeather.startWith(fromStorage).subscribe(new Consumer<Weather>() {
                @Override
                public void accept(@NonNull Weather weather) throws Exception {
                    view.loaded(weather);
                }
            });
            return;
        }

        this.subscription = lastWeather.subscribe(new Consumer<Weather>() {
            @Override
            public void accept(@NonNull Weather weather) throws Exception {
                view.loaded(weather);
            }
        });

        startRefresh();
        view.loadingStart();
    }

    void unbind() {
        if(subscription != null && !subscription.isDisposed()) {
            subscription.dispose();
            subscription = null;
        }

        this.errorHandler = new Consumer<Throwable>() {
            @Override
            public void accept(@NonNull Throwable throwable) throws Exception {
            }
        };
    }

    Disposable startRefresh() {
        Single<Weather> currentWeather = weatherService
                .currentWeather(Locale.getDefault().getLanguage());

        return currentWeather
                .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(weatherStorage.updateLastWeather(), errorHandler);
    }
}