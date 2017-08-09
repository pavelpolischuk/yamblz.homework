package com.gcteam.yamblz.homework.domain.interactor.cities;

import com.gcteam.yamblz.homework.data.object.StoredChosenCity;
import com.gcteam.yamblz.homework.data.repository.cities.CityRepository;
import com.gcteam.yamblz.homework.domain.object.FilteredCity;

import java.util.List;

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


    public Single<StoredChosenCity> addCity(FilteredCity chosenCity) {
        return cityRepository.getCityDetails(chosenCity)
                .doOnSuccess(storedChosenCity -> cityRepository.saveCityDetails(storedChosenCity))
                .subscribeOn(executionScheduler)
                .observeOn(postExecutionScheduler);
    }

    public Single<List<FilteredCity>> getChosenCities() {
        return cityRepository.getCities()
                .subscribeOn(executionScheduler)
                .observeOn(postExecutionScheduler);
    }

    public Single<StoredChosenCity> chooseCity(FilteredCity filteredCity) {
        return cityRepository.getCityDetails(filteredCity)
                .subscribeOn(executionScheduler)
                .observeOn(postExecutionScheduler);
    }
}
