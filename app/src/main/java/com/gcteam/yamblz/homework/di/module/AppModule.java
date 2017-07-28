package com.gcteam.yamblz.homework.di.module;

import android.content.Context;
import android.support.annotation.NonNull;

import com.gcteam.yamblz.homework.settings.PreferencesManager;
import com.gcteam.yamblz.homework.weather.WeatherService;
import com.gcteam.yamblz.homework.weather.WeatherStorage;
import com.gcteam.yamblz.homework.weather.api.OpenWeatherMapApi;
import com.gcteam.yamblz.homework.weather.api.WeatherMapper;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.gcteam.yamblz.homework.weather.api.OpenWeatherMapApi.API_BASE_URL;

/**
 * Created by Kim Michael on 26.07.17
 */
@Module
public class AppModule {

    private Context context;

    public AppModule(@NonNull Context appContext) {
        this.context = appContext;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return context;
    }

    @Provides
    @Singleton
    WeatherService provideWeatherService(PreferencesManager preferencesManager,
                                         WeatherMapper weatherMapper,
                                         OpenWeatherMapApi api) {
        return new WeatherService(preferencesManager, weatherMapper, api);
    }

    @Provides
    @Singleton
    WeatherStorage provideWeatherStorage(PreferencesManager preferencesManager) {
        return new WeatherStorage(preferencesManager);
    }

    @Provides
    @Singleton
    WeatherMapper provideWeatherMapper() {
        return new WeatherMapper();
    }

    @Provides
    @Singleton
    PreferencesManager providePreferencesManager(Context context, Gson gson) {
        return new PreferencesManager(context, gson);
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new Gson();
    }

    @Provides
    @Singleton
    OpenWeatherMapApi provideOpenWeatherMapApi() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        return new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(OpenWeatherMapApi.class);
    }


}
