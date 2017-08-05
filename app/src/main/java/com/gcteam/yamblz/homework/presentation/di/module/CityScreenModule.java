package com.gcteam.yamblz.homework.presentation.di.module;

import com.gcteam.yamblz.homework.domain.interactor.cities.CityFilterInteractor;
import com.gcteam.yamblz.homework.presentation.di.scope.CityChooserScreenScope;
import com.gcteam.yamblz.homework.presentation.presenter.cities.CityChooserPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Kim Michael on 03.08.17
 */
@Module
public class CityScreenModule {

    @Provides
    @CityChooserScreenScope
    public CityChooserPresenter provideCityPresenter(CityFilterInteractor cityFilterInteractor) {
        return new CityChooserPresenter(cityFilterInteractor);
    }
}
