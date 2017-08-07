package com.gcteam.yamblz.homework.domain.interactor.cities;

import com.gcteam.yamblz.homework.data.api.dto.cities.details.CityDetailsResponse;
import com.gcteam.yamblz.homework.data.repository.cities.CityRepository;
import com.gcteam.yamblz.homework.domain.object.FilteredCity;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.Single;

/**
 * Created by Kim Michael on 03.08.17
 */
public class CityPickerInteractor {

    private CityRepository cityRepository;
    private Scheduler executionScheduler;
    private Scheduler postExecutionScheduler;

    @Inject
    public CityPickerInteractor(CityRepository cityRepository,
                                Scheduler executionScheduler,
                                Scheduler postExecutionScheduler) {
        this.cityRepository = cityRepository;
        this.executionScheduler = executionScheduler;
        this.postExecutionScheduler = postExecutionScheduler;
    }


    public Single<CityDetailsResponse> addCity(FilteredCity chosenCity) {
        return cityRepository.getCityDetails(chosenCity)
                .doOnSuccess(cityDetailsResponse -> cityRepository.saveCityDetails(cityDetailsResponse))
                .subscribeOn(executionScheduler)
                .observeOn(postExecutionScheduler);
    }
}
