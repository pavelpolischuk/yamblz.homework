package com.gcteam.yamblz.homework.weather;

import com.gcteam.yamblz.homework.data.local.weather.RoomWeatherStorage;
import com.gcteam.yamblz.homework.data.network.weather.OpenWeatherService;
import com.gcteam.yamblz.homework.data.repository.weather.WeatherRepositoryImpl;
import com.gcteam.yamblz.homework.domain.object.WeatherData;
import com.gcteam.yamblz.homework.presentation.view.weather.WeatherView;

import org.mockito.Mock;

import io.reactivex.functions.Consumer;

/**
 * Created by Kim Michael on 26.07.17
 */
public class WeatherRepositoryTest {

    WeatherData testWeather;
    private WeatherRepositoryImpl weatherRepositoryImpl;
    @Mock
    private WeatherView weatherView;
    @Mock
    private RoomWeatherStorage weatherStorage;
    @Mock
    private OpenWeatherService weatherService;
    @Mock
    private Consumer<WeatherData> consumer;


}
