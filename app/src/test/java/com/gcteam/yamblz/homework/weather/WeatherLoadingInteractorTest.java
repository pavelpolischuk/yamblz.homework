package com.gcteam.yamblz.homework.weather;

import com.gcteam.yamblz.homework.settings.PreferencesManager;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

/**
 * Created by Kim Michael on 26.07.17
 */
public class WeatherLoadingInteractorTest {

    private WeatherLoadingInteractor weatherLoadingInteractor;
    private WeatherLoadingView weatherLoadingView;

    @Before
    public void setup() {
        PreferencesManager preferencesManager = mock(PreferencesManager.class);
        when(preferencesManager.getLat()).thenReturn(20d);
        when(preferencesManager.getLng()).thenReturn(20d);
        weatherLoadingView = mock(WeatherLoadingView.class);

    }

    @Test
    public void testLoading() {
        weatherLoadingInteractor.bind(weatherLoadingView);
        verify(weatherLoadingView).loadingStart();
    }


}
