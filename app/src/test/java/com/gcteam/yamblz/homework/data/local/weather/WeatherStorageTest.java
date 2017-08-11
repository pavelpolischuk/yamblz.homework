package com.gcteam.yamblz.homework.data.local.weather;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.gcteam.yamblz.homework.data.local.AppDatabase;
import com.gcteam.yamblz.homework.domain.object.ForecastData;
import com.gcteam.yamblz.homework.domain.object.FullWeatherReport;
import com.gcteam.yamblz.homework.domain.object.WeatherData;
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

    WeatherStorage weatherStorage;
    FullWeatherReport fullWeatherReport;

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
        WeatherData weatherData = weatherStorage
                .getCurrentWeather(fullWeatherReport.getLat(), fullWeatherReport.getLng())
                .blockingGet();
        ForecastData forecastData = weatherStorage
                .getForecast(fullWeatherReport.getLat(), fullWeatherReport.getLng())
                .blockingGet();
        assertThat(weatherData).isEqualToComparingFieldByField(fullWeatherReport.getWeatherData());
        assertThat(forecastData).isEqualToComparingFieldByField(fullWeatherReport.getForecastData());

    }
}
