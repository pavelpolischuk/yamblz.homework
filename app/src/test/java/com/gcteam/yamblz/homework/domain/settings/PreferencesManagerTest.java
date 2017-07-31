package com.gcteam.yamblz.homework.domain.settings;

import android.content.SharedPreferences;
import android.net.Uri;

import com.gcteam.yamblz.homework.R;
import com.gcteam.yamblz.homework.data.WeatherData;
import com.gcteam.yamblz.homework.utils.PreferencesManager;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.gson.Gson;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import static junit.framework.Assert.*;

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
        testWeather = new WeatherData(
                "", "", "desc", 100, 20d, 10, 12f, R.string.wind_nw, Calendar.getInstance());
        SharedPreferences sp = RuntimeEnvironment.application.getSharedPreferences("", 0);
        preferencesManager = new PreferencesManager(sp, new Gson());
        sp.edit().clear().commit();
    }

    @Test
    public void savedPlaceCanBeLoaded() {
        preferencesManager.savePlace(place);
        assertEquals(lat, preferencesManager.getLat());
        assertEquals(lng, preferencesManager.getLng());
    }

    @Test
    public void savedWeatherCanBeLoaded() {
        preferencesManager.putCurrentWeather(testWeather);
        assertEquals(testWeather, preferencesManager.loadCachedWeather());
    }

    @Test
    public void defaultWeatherDataIsNull() {
        assertNull(preferencesManager.loadCachedWeather());
    }
}
