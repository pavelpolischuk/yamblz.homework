package com.gcteam.yamblz.homework.presentation.di.module;

import com.gcteam.yamblz.homework.data.CityMapper;
import com.gcteam.yamblz.homework.data.api.GooglePlacesAPI;
import com.gcteam.yamblz.homework.data.local.cities.CitiesStorage;
import com.gcteam.yamblz.homework.data.network.cities.CitiesService;
import com.gcteam.yamblz.homework.data.repository.cities.CitiesRepository;
import com.gcteam.yamblz.homework.domain.interactor.cities.CityFilterInteractor;
import com.gcteam.yamblz.homework.presentation.di.scope.CityChooserScope;

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
    @CityChooserScope
    public CityFilterInteractor provideCityInteractor(
            CitiesRepository citiesRepository,
            CityMapper cityMapper,
            @Named(SchedulersModule.JOB) Scheduler executionScheduler,
            @Named(SchedulersModule.UI) Scheduler postExecutionScheduler) {
        return new CityFilterInteractor(
                citiesRepository,
                cityMapper,
                executionScheduler,
                postExecutionScheduler
        );
    }

    @Provides
    @CityChooserScope
    public CitiesRepository provideCitiesRepository(
            CitiesStorage citiesStorage,
            CitiesService citiesService) {
        return new CitiesRepository(citiesStorage, citiesService);
    }

    @Provides
    @CityChooserScope
    public CityMapper provideCityMapper() {
        return new CityMapper();
    }

    @Provides
    @CityChooserScope
    public CitiesService provideCitiesService(GooglePlacesAPI googlePlacesAPI) {
        return new CitiesService(googlePlacesAPI);
    }

    @Provides
    @CityChooserScope
    public CitiesStorage provideCitiesStorage() {
        return new CitiesStorage();
    }
}
