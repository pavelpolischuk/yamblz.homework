package com.gcteam.yamblz.homework.data.local.weather;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.gcteam.yamblz.homework.data.local.AppDatabase;
import com.gcteam.yamblz.homework.domain.object.FullWeatherReport;
import com.google.gson.Gson;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static io.github.benas.randombeans.api.EnhancedRandom.random;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Kim Michael on 11.08.17
 */
@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class WeatherStorageTest {

    private WeatherStorage weatherStorage;
    private FullWeatherReport fullWeatherReport;

    @Before
    public void setup() {
        Context context = RuntimeEnvironment.application.getApplicationContext();
        AppDatabase appDatabase = Room.databaseBuilder(context,
                AppDatabase.class, "weather").allowMainThreadQueries().build();
        weatherStorage = new WeatherStorage(appDatabase, new Gson());
        fullWeatherReport = random(FullWeatherReport.class);
    }

    @Test
    public void saveFullWeatherReport() {
        weatherStorage.saveWeather(fullWeatherReport);

        FullWeatherReport fetchedReport = weatherStorage
                .getFullWeatherReport(this.fullWeatherReport.getLat(), this.fullWeatherReport.getLng())
                .blockingGet();

        assertThat(fetchedReport.getForecastData())
                .isEqualToComparingFieldByField(fullWeatherReport.getForecastData());
        assertThat(fetchedReport.getWeatherData())
                .isEqualToComparingFieldByField(fullWeatherReport.getWeatherData());
    }
}
