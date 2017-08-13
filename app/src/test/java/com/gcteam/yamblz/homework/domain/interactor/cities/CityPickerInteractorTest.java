package com.gcteam.yamblz.homework.domain.interactor.cities;

import com.gcteam.yamblz.homework.data.object.StoredCity;
import com.gcteam.yamblz.homework.data.repository.cities.CityRepositoryImpl;
import com.gcteam.yamblz.homework.domain.object.FilteredCity;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

import io.reactivex.Single;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.TestScheduler;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Kim Michael on 11.08.17
 */
public class CityPickerInteractorTest {

    private CityPickerInteractor cityPickerInteractor;

    @Mock
    private CityRepositoryImpl cityRepositoryImpl;
    @Mock
    private ExecutorService executorService;

    @Mock
    private FilteredCity filteredCity;
    @Mock
    private StoredCity storedCity;
    private List<FilteredCity> filteredCities;

    private TestScheduler executionScheduler;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        executionScheduler = new TestScheduler();
        cityPickerInteractor = new CityPickerInteractor(
                cityRepositoryImpl,
                executionScheduler,
                executionScheduler,
                executorService
        );
        filteredCities = new ArrayList<>();
        filteredCities.add(filteredCity);
    }

    @Test
    public void canGetDetailsForCity_thenSaveThem() {
        TestObserver<StoredCity> testObserver;
        when(cityRepositoryImpl.saveChosenCityDetails(filteredCity)).thenReturn(Single.just(storedCity));
        testObserver = cityPickerInteractor.addCity(filteredCity).test();
        executionScheduler.triggerActions();
        testObserver.awaitTerminalEvent();
        verify(cityRepositoryImpl).saveChosenCityDetails(storedCity);
        testObserver.assertNoErrors();
        testObserver.assertValue(storedCity);
    }

    @Test
    public void canFetchChosenCities() {
        TestObserver<List<FilteredCity>> testObserver;
        when(cityRepositoryImpl.getCities()).thenReturn(Single.just(filteredCities));
        testObserver = cityPickerInteractor.getChosenCities().test();
        executionScheduler.triggerActions();
        testObserver.awaitTerminalEvent();
        verify(cityRepositoryImpl).getCities();
        testObserver.assertNoErrors();
        testObserver.assertValue(filteredCities);
    }

    @Test
    public void saveChosenCity_delegatesToRepository() {
        when(cityRepositoryImpl.saveChosenCityDetails(filteredCity)).thenReturn(Single.just(storedCity));

        TestObserver<StoredCity> testObserver = cityPickerInteractor.chooseCity(filteredCity).test();
        executionScheduler.triggerActions();

        verify(cityRepositoryImpl).saveChosenCityDetails(filteredCity);
    }

    @Test
    public void setNoChosenCity_delegatesToRepository() {
        cityPickerInteractor.setNoChosenCity();
        verify(cityRepositoryImpl).setNoChosenCity();
    }


}
