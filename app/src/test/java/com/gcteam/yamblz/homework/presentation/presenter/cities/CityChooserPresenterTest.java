package com.gcteam.yamblz.homework.presentation.presenter.cities;

import com.gcteam.yamblz.homework.domain.interactor.cities.CityFilterInteractor;
import com.gcteam.yamblz.homework.presentation.view.cities.CityChooserView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.Observable;

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
    private CityChooserView cityChooserView;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        cityChooserPresenter = new CityChooserPresenter(cityFilterInteractor);
    }

    @Test
    public void onAttach_bindsToEditText_showsFilteredCities() {
        when(cityChooserView.getFilterObs()).thenReturn(Observable.just("input"));
        cityChooserPresenter.onAttach(cityChooserView);
        verify(cityChooserView).getFilterObs();
        verify(cityFilterInteractor).getCitiesByFilter("input");
    }
}
