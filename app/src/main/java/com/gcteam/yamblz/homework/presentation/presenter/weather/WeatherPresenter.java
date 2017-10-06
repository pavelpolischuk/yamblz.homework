package com.gcteam.yamblz.homework.presentation.presenter.weather;

import com.gcteam.yamblz.homework.domain.interactor.weather.WeatherInteractor;
import com.gcteam.yamblz.homework.presentation.BasePresenter;
import com.gcteam.yamblz.homework.presentation.view.weather.WeatherView;
import com.gcteam.yamblz.homework.utils.PreferencesManager;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import timber.log.Timber;

/**
 * Created by Kim Michael on 02.08.17
 */
public class WeatherPresenter extends BasePresenter<WeatherView> {

    private final WeatherInteractor weatherInteractor;
    private CompositeDisposable compositeDisposable;

    @Inject
    public WeatherPresenter(WeatherInteractor weatherInteractor) {
        this.weatherInteractor = weatherInteractor;
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        compositeDisposable.clear();
    }

    public void refreshForecast(PreferencesManager preferencesManager, boolean forceUpdate,
                                boolean showLoading) {
        if (preferencesManager.getChosenCity().equals(PreferencesManager.NO_CHOSEN_CITY)) {
            if (getView() != null) {
                getView().showEmptyView();
            }
            return;
        }
        if (getView() != null && showLoading) {
            getView().showLoadingStarted();
        }
        double lat = preferencesManager.getLat();
        double lng = preferencesManager.getLng();
        Timber.d("Subscribed on weather interactor");
        compositeDisposable.add(
                weatherInteractor.getWeather(lat, lng, forceUpdate)
                        .subscribe(fullWeatherReport -> {
                            if (getView() != null) {
                                getView().showFullWeather(fullWeatherReport);
                                getView().changeTitle(preferencesManager.getChosenCity());
                            }
                        }, throwable -> {
                            if (getView() != null) {
                                getView().showErrorMessage();
                            }
                        }));
    }
}
