package com.gcteam.yamblz.homework.data.local.cities;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.gcteam.yamblz.homework.data.local.AppDatabase;
import com.gcteam.yamblz.homework.data.object.StoredCity;
import com.gcteam.yamblz.homework.domain.object.FilteredCity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.List;

import static io.github.benas.randombeans.api.EnhancedRandom.random;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Kim Michael on 11.08.17
 */
@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class RoomCityStorageTest {

    CityStorage roomCityStorage;
    StoredCity storedCity;
    StoredCity secondStoredCity;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        Context context = RuntimeEnvironment.application.getApplicationContext();
        AppDatabase appDatabase = Room.databaseBuilder(context,
                AppDatabase.class, "weather").allowMainThreadQueries().build();
        roomCityStorage = new RoomCityStorage(appDatabase);
        storedCity = random(StoredCity.class);
        secondStoredCity = random(StoredCity.class);
    }

    @Test
    public void getSavedCities() {
        roomCityStorage.saveCityDetails(storedCity);
        List<StoredCity> cities = roomCityStorage.getChosenCities().blockingGet();
        assertThat(cities).containsExactly(storedCity);
    }

    @Test
    public void deleteSavedCities() {
        roomCityStorage.saveCityDetails(storedCity);
        roomCityStorage.saveCityDetails(secondStoredCity);

        List<StoredCity> cities = roomCityStorage.getChosenCities().blockingGet();
        assertThat(cities).containsExactlyInAnyOrder(secondStoredCity, storedCity);

        roomCityStorage.deleteCity(new FilteredCity(storedCity.getCityName(),
                storedCity.getCountryName(),
                storedCity.getPlaceId(),
                storedCity.getPriority()));

        List<StoredCity> citiesAfterDeletion = roomCityStorage.getChosenCities().blockingGet();
        assertThat(citiesAfterDeletion).containsExactlyInAnyOrder(secondStoredCity);
    }

    @Test
    public void getChosenCity() {
        roomCityStorage.saveCityDetails(storedCity);
        StoredCity retrievedCity = roomCityStorage.getChosenCity(new FilteredCity(
                storedCity.getCityName(),
                storedCity.getCountryName(),
                storedCity.getPlaceId(),
                storedCity.getPriority()))
                .blockingGet();
        assertThat(retrievedCity).isEqualToComparingFieldByField(storedCity);
    }
}
