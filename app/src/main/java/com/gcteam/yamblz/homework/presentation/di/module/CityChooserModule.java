package com.gcteam.yamblz.homework.presentation.di.module;

import com.gcteam.yamblz.homework.data.repository.cities.CityRepositoryImpl;
import com.gcteam.yamblz.homework.domain.interactor.cities.CityPickerInteractor;
import com.gcteam.yamblz.homework.presentation.di.scope.CityChooserScope;
import com.gcteam.yamblz.homework.presentation.presenter.city.CityPickerPresenter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;

/**
 * Created by Kim Michael on 05.08.17
 */
@Module
public class CityChooserModule {

    @Provides
    @CityChooserScope
    public CityPickerPresenter provideCityPickerPresenter(CityPickerInteractor cityPickerInteractor) {
        return new CityPickerPresenter(cityPickerInteractor);
    }

    @Provides
    @CityChooserScope
    public CityPickerInteractor provideCityPickerInteractor(
            CityRepositoryImpl cityRepositoryImpl,
            @Named(SchedulersModule.JOB) Scheduler executionScheduler,
            @Named(SchedulersModule.UI) Scheduler postExecutionScheduler,
            ExecutorService executorService) {
        return new CityPickerInteractor(cityRepositoryImpl, executionScheduler, postExecutionScheduler, executorService);
    }

    @Provides
    @CityChooserScope
    public ExecutorService provideExecutorService() {
        return Executors.newFixedThreadPool(3);
    }
}
