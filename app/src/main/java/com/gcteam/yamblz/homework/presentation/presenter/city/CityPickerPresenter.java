package com.gcteam.yamblz.homework.presentation.presenter.city;

import com.gcteam.yamblz.homework.domain.interactor.cities.CityPickerInteractor;
import com.gcteam.yamblz.homework.domain.object.FilteredCity;
import com.gcteam.yamblz.homework.presentation.BasePresenter;
import com.gcteam.yamblz.homework.presentation.view.main.CityChooserView;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * Created by Kim Michael on 05.08.17
 */
public class CityPickerPresenter extends BasePresenter<CityChooserView> {

    private CityPickerInteractor cityPickerInteractor;
    private Disposable addCityDisposable;

    @Inject
    public CityPickerPresenter(CityPickerInteractor cityPickerInteractor) {
        this.cityPickerInteractor = cityPickerInteractor;
    }

    public void addCity(FilteredCity chosenCity) {
        addCityDisposable = cityPickerInteractor.addCity(chosenCity)
                .subscribe();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        addCityDisposable.dispose();
    }
}
