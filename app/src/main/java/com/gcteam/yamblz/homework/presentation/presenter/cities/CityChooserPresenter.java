package com.gcteam.yamblz.homework.presentation.presenter.cities;

import android.support.annotation.NonNull;

import com.gcteam.yamblz.homework.domain.interactor.cities.CityFilterInteractor;
import com.gcteam.yamblz.homework.presentation.BasePresenter;
import com.gcteam.yamblz.homework.presentation.view.cities.CityChooserView;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * Created by Kim Michael on 03.08.17
 */
public class CityChooserPresenter extends BasePresenter<CityChooserView> {

    private CityFilterInteractor cityFilterInteractor;
    private Disposable filterDisposable;

    @Inject
    public CityChooserPresenter(CityFilterInteractor cityFilterInteractor) {
        this.cityFilterInteractor = cityFilterInteractor;
    }

    @Override
    public void onAttach(@NonNull CityChooserView view) {
        super.onAttach(view);
        filterDisposable =
                view.getFilterObs().subscribe(s -> cityFilterInteractor.getCitiesByFilter(s));
    }

    @Override
    public void onDetach() {
        super.onDetach();
        filterDisposable.dispose();
    }

}
