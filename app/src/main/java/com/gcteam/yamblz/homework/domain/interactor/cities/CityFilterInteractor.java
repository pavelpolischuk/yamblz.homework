package com.gcteam.yamblz.homework.domain.interactor.cities;

import com.gcteam.yamblz.homework.data.CityMapper;
import com.gcteam.yamblz.homework.data.repository.cities.CitiesRepository;
import com.gcteam.yamblz.homework.domain.object.FilteredCity;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.Single;

/**
 * Created by Kim Michael on 03.08.17
 */
public class CityFilterInteractor {

    private CitiesRepository cityRepository;
    private CityMapper cityMapper;
    private Scheduler executionScheduler;
    private Scheduler postExecutionScheduler;

    @Inject
    public CityFilterInteractor(
            CitiesRepository cityRepository,
            CityMapper cityMapper,
            Scheduler executionScheduler,
            Scheduler postExecutionScheduler) {
        this.cityRepository = cityRepository;
        this.cityMapper = cityMapper;
        this.executionScheduler = executionScheduler;
        this.postExecutionScheduler = postExecutionScheduler;
    }

    public Single<List<FilteredCity>> getCitiesByFilter(String input) {
        return cityRepository.getCitiesByFilter(input)
                .map(cityMapper)
                .subscribeOn(executionScheduler)
                .observeOn(postExecutionScheduler);
    }
}
