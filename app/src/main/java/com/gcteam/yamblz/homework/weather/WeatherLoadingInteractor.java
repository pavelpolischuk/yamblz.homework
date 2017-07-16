package com.gcteam.yamblz.homework.weather;

import com.gcteam.yamblz.homework.weather.api.Weather;

import java.util.Locale;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by turist on 16.07.2017.
 */

public class WeatherLoadingInteractor {

    private static final int MOSCOW_CITY_ID = 524901;
    private static final String METRIC_UNITS = "metric";

    private Disposable subscription;
    private Consumer<Throwable> errorHandler;

    void bind(final WeatherLoadingView view) {
        this.errorHandler = new Consumer<Throwable>() {
            @Override
            public void accept(@NonNull Throwable throwable) throws Exception {
                view.loadingError();
            }
        };

        Observable<Weather> lastWeather = WeatherStorage.get().lastWeather();
        Weather fromStorage = WeatherStorage.get().load();
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
        Observable<Weather> currentWeather = WeatherService.get()
                .currentWeather(MOSCOW_CITY_ID, METRIC_UNITS, Locale.getDefault().getLanguage());

        return currentWeather
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(WeatherStorage.get().updateLastWeather(), errorHandler);
    }
}