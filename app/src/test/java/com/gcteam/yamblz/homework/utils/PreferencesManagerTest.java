package com.gcteam.yamblz.homework.utils;

import android.content.SharedPreferences;
import android.net.Uri;

import com.gcteam.yamblz.homework.data.api.dto.cities.details.CityDetailsResponse;
import com.gcteam.yamblz.homework.data.object.StoredCity;
import com.gcteam.yamblz.homework.domain.object.WeatherData;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.List;
import java.util.Locale;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Kim Michael on 29.07.17
 */
@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class PreferencesManagerTest {

    PreferencesManager preferencesManager;


    Place place;
    WeatherData testWeather;
    final String name = "Moscow";

    final String id = "123";
    final double lat = 10;
    final double lng = 15;
    final CityDetailsResponse cityDetailsResponse = new CityDetailsResponse();
    final StoredCity storedCity = new StoredCity("city", "city", 1, 1.0d, 10.0d, "asdf", "country");

    @Before
    public void setup() {
        place = new Place() {
            @Override
            public String getId() {
                return id;
            }

            @Override
            public List<Integer> getPlaceTypes() {
                return null;
            }

            @Override
            public CharSequence getAddress() {
                return null;
            }

            @Override
            public Locale getLocale() {
                return Locale.US;
            }

            @Override
            public CharSequence getName() {
                return name;
            }

            @Override
            public LatLng getLatLng() {
                return new LatLng(lat, lng);
            }

            @Override
            public LatLngBounds getViewport() {
                return null;
            }

            @Override
            public Uri getWebsiteUri() {
                return null;
            }

            @Override
            public CharSequence getPhoneNumber() {
                return null;
            }

            @Override
            public float getRating() {
                return 0;
            }

            @Override
            public int getPriceLevel() {
                return 0;
            }

            @Override
            public CharSequence getAttributions() {
                return null;
            }

            @Override
            public Place freeze() {
                return null;
            }

            @Override
            public boolean isDataValid() {
                return false;
            }
        };
        testWeather = new WeatherData(123, "123", 20.0d, 15.0d, 30.0d, 200d, 20, 2, 200, 38485837L);
        SharedPreferences sp = RuntimeEnvironment.application.getSharedPreferences("", 0);
        preferencesManager = new PreferencesManager(sp);
        sp.edit().clear().apply();
    }

    @Test
    public void savedCityNameLatLngCanBeLoaded() {
        preferencesManager.saveChosenCity(storedCity);
        assertEquals(storedCity.getCityName(), preferencesManager.getChosenCity());
        assertEquals(storedCity.getLat(), preferencesManager.getLat());
        assertEquals(storedCity.getLng(), preferencesManager.getLng());
        assertEquals((int) storedCity.getPriority(), preferencesManager.getChosenCityId());
    }

    @Test
    public void ifNoChosenCity_SpecialValueWillBeReturned() throws Exception {
        preferencesManager.saveNoChosenCity();
        assertEquals(PreferencesManager.NO_CHOSEN_CITY, preferencesManager.getChosenCity());
    }

    @Test
    public void intervalNotSpecified_defaultUpdateIntervalIsReturned() {
        assertEquals(preferencesManager.getUpdateInterval(), (int)(Integer.valueOf(PreferencesManager.DEFAULT_UPDATE_INTERVAL)));
    }

}
