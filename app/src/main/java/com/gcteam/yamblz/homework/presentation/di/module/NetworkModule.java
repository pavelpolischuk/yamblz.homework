package com.gcteam.yamblz.homework.presentation.di.module;

import com.gcteam.yamblz.homework.data.api.OpenWeatherMapApi;

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
 * Created by Kim Michael on 01.08.17
 */
@Module
public class NetworkModule {

    @Provides
    @Singleton
    OpenWeatherMapApi provideOpenWeatherMapApi(OkHttpClient client) {

        return new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(OpenWeatherMapApi.class);
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        return client;
    }
}
