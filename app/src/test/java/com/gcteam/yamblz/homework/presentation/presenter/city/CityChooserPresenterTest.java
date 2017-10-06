package com.gcteam.yamblz.homework.presentation.presenter.city;

import com.gcteam.yamblz.homework.domain.interactor.cities.CityFilterInteractor;
import com.gcteam.yamblz.homework.presentation.view.city.CityFilterView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.Observable;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Kim Michael on 04.08.17
 */
public class CityChooserPresenterTest {

    private CityChooserPresenter cityChooserPresenter;
    @Mock
    private CityFilterInteractor cityFilterInteractor;
    @Mock
    private CityFilterView cityFilterView;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        cityChooserPresenter = new CityChooserPresenter(cityFilterInteractor);
    }

    @Test
    public void onAttach_bindsToEditText_showsFilteredCities() {
        when(cityFilterView.getFilterObs()).thenReturn(Observable.just("input"));
        cityChooserPresenter.onAttach(cityFilterView);
        verify(cityFilterView).showLoadingSpinner();
        verify(cityFilterView).getFilterObs();
        verify(cityFilterInteractor).getCitiesByFilter("input");
    }

    @Test
    public void whenTypedNothing_dontGoToNetwork_hideUI() {
        when(cityFilterView.getFilterObs()).thenReturn(Observable.just(""));
        cityChooserPresenter.onAttach(cityFilterView);
        verify(cityFilterView).hideLoadingSpinner();
        verify(cityFilterView).hideChosenCities();
        verify(cityFilterInteractor, never()).getCitiesByFilter(anyString());
    }
}
