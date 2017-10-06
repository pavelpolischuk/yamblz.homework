package com.gcteam.yamblz.homework.domain.interactor.cities;

import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;

import com.gcteam.yamblz.homework.data.repository.cities.CityRepository;
import com.gcteam.yamblz.homework.domain.object.FilteredCity;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import timber.log.Timber;

/**
 * Created by Kim Michael on 03.08.17
 */
public class CityFilterInteractor {

    private CityRepository cityRepository;
    private Scheduler executionScheduler;
    private Scheduler postExecutionScheduler;

    @Inject
    public CityFilterInteractor(
            CityRepository cityRepository,
            Scheduler executionScheduler,
            Scheduler postExecutionScheduler) {
        this.cityRepository = cityRepository;
        this.executionScheduler = executionScheduler;
        this.postExecutionScheduler = postExecutionScheduler;
    }

    @WorkerThread
    @NonNull
    public Single<List<FilteredCity>> getCitiesByFilter(@NonNull String input) {
        Timber.d("Getting cities by user input : %s", input);
        return cityRepository.getCitiesByFilter(input)
                .subscribeOn(executionScheduler)
                .observeOn(postExecutionScheduler);
    }
}
