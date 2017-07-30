package com.gcteam.yamblz.homework.settings;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static com.gcteam.yamblz.homework.settings.SettingsInteractor.CHOOSE_CITY_KEY;
import static com.gcteam.yamblz.homework.settings.SettingsInteractor.UPDATE_INTERVAL_KEY;
import static org.mockito.Mockito.*;

/**
 * Created by Kim Michael on 29.07.17
 */
public class SettingsInteractorTest {

    SettingsInteractor settingsInteractor;
    @Mock
    SettingsView settingsView;
    @Mock
    PreferencesManager preferencesManager;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        settingsInteractor = new SettingsInteractor(settingsView, preferencesManager);
    }

    @Test
    public void initializingInteractorUpdatesSummaries() {
        int interval = 10;
        String city = "Moscow";
        when(preferencesManager.getUpdateInterval()).thenReturn(interval);
        when(preferencesManager.getChosenCity()).thenReturn(city);
        settingsInteractor.initView();
        verify(settingsView).updateSummary(UPDATE_INTERVAL_KEY, String.valueOf(interval));
        verify(settingsView).updateSummary(CHOOSE_CITY_KEY, city);
    }

}
