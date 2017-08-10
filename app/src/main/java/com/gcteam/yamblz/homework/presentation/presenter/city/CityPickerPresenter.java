package com.gcteam.yamblz.homework.presentation.presenter.city;

import android.support.annotation.NonNull;

import com.gcteam.yamblz.homework.domain.interactor.cities.CityPickerInteractor;
import com.gcteam.yamblz.homework.domain.object.FilteredCity;
import com.gcteam.yamblz.homework.presentation.BasePresenter;
import com.gcteam.yamblz.homework.presentation.view.main.CityChooserView;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Kim Michael on 05.08.17
 */
public class CityPickerPresenter extends BasePresenter<CityChooserView> {

    private CityPickerInteractor cityPickerInteractor;
    private CompositeDisposable compositeDisposable;

    @Inject
    public CityPickerPresenter(CityPickerInteractor cityPickerInteractor) {
        this.cityPickerInteractor = cityPickerInteractor;
        compositeDisposable = new CompositeDisposable();
    }

    public void addCity(FilteredCity chosenCity) {
        compositeDisposable.add(cityPickerInteractor.addCity(chosenCity)
                .subscribe());
    }

    @Override
    public void onAttach(@NonNull CityChooserView view) {
        super.onAttach(view);
        compositeDisposable.add(cityPickerInteractor.getChosenCities()
                .subscribe(chosenCities -> {
                    if (getView() != null) {
                        getView().showChosenCities(chosenCities);
                    }
                }));
    }

    @Override
    public void onDetach() {
        super.onDetach();
        compositeDisposable.clear();
    }

    public void chooseCity(FilteredCity filteredCity) {
        compositeDisposable.add(cityPickerInteractor.chooseCity(filteredCity)
                .subscribe());
    }

    public void deleteCity(FilteredCity filteredCity) {
        cityPickerInteractor.deleteCity(filteredCity);
    }

    public void setNoChosenCity() {
        cityPickerInteractor.setNoChosenCity();
    }
}
