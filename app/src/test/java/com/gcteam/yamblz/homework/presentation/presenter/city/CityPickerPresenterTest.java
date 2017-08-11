package com.gcteam.yamblz.homework.presentation.presenter.city;

import com.gcteam.yamblz.homework.data.object.StoredCity;
import com.gcteam.yamblz.homework.domain.interactor.cities.CityPickerInteractor;
import com.gcteam.yamblz.homework.domain.object.FilteredCity;
import com.gcteam.yamblz.homework.presentation.view.main.CityChooserView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Kim Michael on 11.08.17
 */
public class CityPickerPresenterTest {
    CityPickerPresenter cityPickerPresenter;

    @Mock
    CityPickerInteractor cityPickerInteractor;
    @Mock
    CityChooserView cityChooserView;
    private FilteredCity filteredCity;
    private StoredCity storedCity;

    private List<FilteredCity> filteredCities;
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        cityPickerPresenter = new CityPickerPresenter(cityPickerInteractor);
        filteredCity = mock(FilteredCity.class);
        storedCity = mock(StoredCity.class);
        filteredCities = new ArrayList<>();
        filteredCities.add(filteredCity);
    }

    @Test
    public void onAttach_getChosenCities_showThem() {
        when(cityPickerInteractor.getChosenCities()).thenReturn(Single.just(filteredCities));
        cityPickerPresenter.onAttach(cityChooserView);
        verify(cityPickerInteractor).getChosenCities();
        verify(cityChooserView).showChosenCities(filteredCities);
    }

    @Test
    public void canChooseCity() throws Exception {
        when(cityPickerInteractor.chooseCity(filteredCity)).thenReturn(Single.just(storedCity));
        cityPickerPresenter.chooseCity(filteredCity);
        verify(cityPickerInteractor).chooseCity(filteredCity);
    }

    @Test
    public void canAddCity() throws Exception {
        when(cityPickerInteractor.addCity(filteredCity)).thenReturn(Single.just(storedCity));
        cityPickerPresenter.addCity(filteredCity);
        verify(cityPickerInteractor).addCity(filteredCity);
    }

    @Test
    public void canDeleteCity() throws Exception {
        cityPickerPresenter.deleteCity(filteredCity);
        verify(cityPickerInteractor).deleteCity(filteredCity);
    }

    @Test
    public void canSetNoChosenCity() throws Exception {
        cityPickerPresenter.setNoChosenCity();
        verify(cityPickerInteractor).setNoChosenCity();
    }
}
