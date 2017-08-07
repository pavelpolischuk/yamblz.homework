package com.gcteam.yamblz.homework.domain.interactor.cities;

import android.support.annotation.NonNull;

import com.gcteam.yamblz.homework.data.CitiesResponseMapper;
import com.gcteam.yamblz.homework.data.repository.cities.CityRepository;
import com.gcteam.yamblz.homework.domain.object.FilteredCity;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.Single;

/**
 * Created by Kim Michael on 03.08.17
 */
public class CityFilterInteractor {

    private CityRepository cityRepository;
    private CitiesResponseMapper citiesResponseMapper;
    private Scheduler executionScheduler;
    private Scheduler postExecutionScheduler;

    @Inject
    public CityFilterInteractor(
            CityRepository cityRepository,
            CitiesResponseMapper citiesResponseMapper,
            Scheduler executionScheduler,
            Scheduler postExecutionScheduler) {
        this.cityRepository = cityRepository;
        this.citiesResponseMapper = citiesResponseMapper;
        this.executionScheduler = executionScheduler;
        this.postExecutionScheduler = postExecutionScheduler;
    }

    public Single<List<FilteredCity>> getCitiesByFilter(@NonNull String input) {
        return cityRepository.getCitiesByFilter(input)
                .map(citiesResponseMapper)
                .subscribeOn(executionScheduler)
                .observeOn(postExecutionScheduler);
    }
}
