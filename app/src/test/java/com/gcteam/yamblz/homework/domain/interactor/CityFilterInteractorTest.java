package com.gcteam.yamblz.homework.domain.interactor;

import com.gcteam.yamblz.homework.data.CityMapper;
import com.gcteam.yamblz.homework.data.repository.cities.CitiesRepository;
import com.gcteam.yamblz.homework.domain.interactor.cities.CityFilterInteractor;
import com.gcteam.yamblz.homework.domain.object.FilteredCity;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.TestScheduler;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Kim Michael on 04.08.17
 */
public class CityFilterInteractorTest {

    private CityFilterInteractor cityFilterInteractor;
    @Mock
    private CitiesRepository citiesRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        TestScheduler executionScheduler = new TestScheduler();
        TestScheduler postExecutionScheduler = new TestScheduler();
        cityFilterInteractor = new CityFilterInteractor(
                citiesRepository,
                new CityMapper(),
                executionScheduler,
                postExecutionScheduler
        );
    }

    @Test
    public void getsCitiesFromRepositoryByInput() {
        FilteredCity filteredCity1 = mock(FilteredCity.class);
        FilteredCity filteredCity2 = mock(FilteredCity.class);
        FilteredCity filteredCity3 = mock(FilteredCity.class);
        List<FilteredCity> filteredCities = new ArrayList<>();
        filteredCities.add(filteredCity1);
        filteredCities.add(filteredCity2);
        filteredCities.add(filteredCity3);

        when(citiesRepository.getCitiesByFilter("input")).thenReturn(Observable.just(filteredCities));

        Observable<List<FilteredCity>> cities =
                cityFilterInteractor.getCitiesByFilter("input");
        verify(citiesRepository).getCitiesByFilter("input");

    }

}
