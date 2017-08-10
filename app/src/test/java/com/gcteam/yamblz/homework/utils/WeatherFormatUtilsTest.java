package com.gcteam.yamblz.homework.utils;

import com.gcteam.yamblz.homework.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;

/**
 * Created by Kim Michael on 10.08.17
 */
@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class WeatherFormatUtilsTest {

    private static final long tenthAugustMillis = 1502371928085L;

    @Before
    public void setup() {

    }

    @Test
    public void getArtResourceForWeatherCondition() {
        assertEquals(R.drawable.ic_rain, WeatherFormatUtils.getArtResourceForWeatherCondition(503));
        assertEquals(R.drawable.ic_snow, WeatherFormatUtils.getArtResourceForWeatherCondition(620));
        assertEquals(R.drawable.ic_storm, WeatherFormatUtils.getArtResourceForWeatherCondition(220));
        assertEquals(R.drawable.ic_light_rain, WeatherFormatUtils.getArtResourceForWeatherCondition(320));
        assertEquals(R.drawable.ic_light_clouds, WeatherFormatUtils.getArtResourceForWeatherCondition(801));
        assertEquals(R.drawable.ic_fog, WeatherFormatUtils.getArtResourceForWeatherCondition(720));
        assertEquals(R.drawable.ic_clear, WeatherFormatUtils.getArtResourceForWeatherCondition(800));
        assertEquals(R.drawable.ic_cloudy, WeatherFormatUtils.getArtResourceForWeatherCondition(803));
    }

    @Test
    public void getIconResourceForWeatherCondition() throws Exception {

    }

    @Test
    public void formatTemperature() throws Exception {

    }

    @Test
    public void isMetric() throws Exception {

    }

    @Test
    public void getFormattedWind() throws Exception {

    }

    @Test
    public void getFormattedDate() throws Exception {

    }

    @Test
    public void getFormattedDay() throws Exception {

    }

    @Test
    public void getFormattedDayAndDate() throws Exception {

    }

    @Test
    public void formatDescription() throws Exception {

    }
}
