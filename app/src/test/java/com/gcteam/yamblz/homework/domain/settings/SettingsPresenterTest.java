package com.gcteam.yamblz.homework.domain.settings;

import com.gcteam.yamblz.homework.presentation.view.settings.SettingsView;
import com.gcteam.yamblz.homework.utils.PreferencesManager;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.gcteam.yamblz.homework.domain.settings.SettingsPresenter.CHOOSE_CITY_KEY;
import static com.gcteam.yamblz.homework.domain.settings.SettingsPresenter.UPDATE_INTERVAL_KEY;
import static org.mockito.Mockito.*;

/**
 * Created by Kim Michael on 29.07.17
 */
public class SettingsPresenterTest {

    SettingsPresenter settingsPresenter;
    @Mock
    SettingsView settingsView;
    @Mock
    PreferencesManager preferencesManager;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        settingsPresenter = new SettingsPresenter(settingsView, preferencesManager);
    }

    @Test
    public void initializingInteractorUpdatesSummaries() {
        int interval = 10;
        String city = "Moscow";
        when(preferencesManager.getUpdateInterval()).thenReturn(interval);
        when(preferencesManager.getChosenCity()).thenReturn(city);
        settingsPresenter.initView();
        verify(settingsView).updateSummary(UPDATE_INTERVAL_KEY, String.valueOf(interval));
        verify(settingsView).updateSummary(CHOOSE_CITY_KEY, city);
    }

}
