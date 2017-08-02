package com.gcteam.yamblz.homework.presentation.di.module;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.preference.PreferenceManager;

import com.gcteam.yamblz.homework.utils.PreferencesManager;
import com.gcteam.yamblz.homework.data.network.WeatherService;
import com.gcteam.yamblz.homework.data.local.WeatherStorage;
import com.gcteam.yamblz.homework.data.api.OpenWeatherMapApi;
import com.gcteam.yamblz.homework.data.WeatherMapper;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.gcteam.yamblz.homework.data.api.OpenWeatherMapApi.API_BASE_URL;

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


}
