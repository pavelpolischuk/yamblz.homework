package com.gcteam.yamblz.homework.presentation.view.city;

import android.support.annotation.NonNull;

import com.gcteam.yamblz.homework.domain.object.FilteredCity;
import com.gcteam.yamblz.homework.presentation.BaseView;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Kim Michael on 03.08.17
 */
public interface CityFilterView extends BaseView {

    void showLoadingSpinner();

    void hideLoadingSpinner();

    void showChosenCities(@NonNull List<FilteredCity> citySummaries);

    void hideChosenCities();

    Observable<String> getFilterObs();

    void showError();
}
