package com.gcteam.yamblz.homework.weather;

import com.gcteam.yamblz.homework.data.local.weather.WeatherStorage;
import com.gcteam.yamblz.homework.data.network.weather.WeatherService;
import com.gcteam.yamblz.homework.data.repository.weather.WeatherRepository;
import com.gcteam.yamblz.homework.domain.object.WeatherData;
import com.gcteam.yamblz.homework.presentation.view.weather.WeatherView;

import org.mockito.Mock;

import io.reactivex.functions.Consumer;

/**
 * Created by Kim Michael on 26.07.17
 */
public class WeatherRepositoryTest {

    private WeatherRepository weatherRepository;
    @Mock
    private WeatherView weatherView;
    @Mock
    private WeatherStorage weatherStorage;
    @Mock
    private WeatherService weatherService;
    @Mock
    private Consumer<WeatherData> consumer;

    WeatherData testWeather;


}
