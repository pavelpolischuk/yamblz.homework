package com.gcteam.yamblz.homework.presentation.di.module;

import com.gcteam.yamblz.homework.data.CitiesResponseMapper;
import com.gcteam.yamblz.homework.data.api.GooglePlacesAPI;
import com.gcteam.yamblz.homework.data.local.cities.CityStorage;
import com.gcteam.yamblz.homework.data.network.cities.CityService;
import com.gcteam.yamblz.homework.data.repository.cities.CityRepository;
import com.gcteam.yamblz.homework.domain.interactor.cities.CityFilterInteractor;
import com.gcteam.yamblz.homework.presentation.di.scope.CityFilterScope;
import com.gcteam.yamblz.homework.utils.PreferencesManager;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;

/**
 * Created by Kim Michael on 03.08.17
 */
@Module
public class CityModule {

    @Provides
    @CityFilterScope
    public CityFilterInteractor provideCityInteractor(
            CityRepository cityRepository,
            CitiesResponseMapper citiesResponseMapper,
            @Named(SchedulersModule.JOB) Scheduler executionScheduler,
            @Named(SchedulersModule.UI) Scheduler postExecutionScheduler) {
        return new CityFilterInteractor(
                cityRepository,
                executionScheduler,
                postExecutionScheduler
        );
    }

    @Provides
    @CityFilterScope
    public CityRepository provideCitiesRepository(
            CityStorage cityStorage,
            CityService cityService,
            PreferencesManager preferencesManager,
            CitiesResponseMapper citiesResponseMapper) {
        return new CityRepository(cityStorage, cityService, preferencesManager, citiesResponseMapper);
    }

    @Provides
    @CityFilterScope
    public CitiesResponseMapper provideCityMapper() {
        return new CitiesResponseMapper();
    }

    @Provides
    @CityFilterScope
    public CityService provideCitiesService(GooglePlacesAPI googlePlacesAPI) {
        return new CityService(googlePlacesAPI);
    }
}
