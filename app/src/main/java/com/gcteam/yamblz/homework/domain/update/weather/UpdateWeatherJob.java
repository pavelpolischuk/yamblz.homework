package com.gcteam.yamblz.homework.domain.update.weather;

import android.support.annotation.NonNull;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobManager;
import com.evernote.android.job.JobRequest;
import com.gcteam.yamblz.homework.data.local.weather.WeatherStorage;
import com.gcteam.yamblz.homework.data.network.weather.WeatherService;
import com.gcteam.yamblz.homework.domain.object.WeatherData;
import com.gcteam.yamblz.homework.presentation.di.component.AppComponent;
import com.gcteam.yamblz.homework.presentation.di.component.DaggerAppComponent;
import com.gcteam.yamblz.homework.presentation.di.module.AppModule;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by turist on 16.07.2017.
 */

public class UpdateWeatherJob extends Job {

    public static final String TAG = "current_weather_update_job";

    @Inject
    WeatherService weatherService;
    @Inject
    WeatherStorage weatherStorage;

    AppComponent appComponent;

    public static void startUpdate(int minutesInterval) {
        new JobRequest.Builder(UpdateWeatherJob.TAG)
                .setPeriodic(TimeUnit.MINUTES.toMillis(minutesInterval), TimeUnit.MINUTES.toMillis(15))
                .setRequiredNetworkType(JobRequest.NetworkType.CONNECTED)
                .setRequirementsEnforced(true)
                .setPersisted(true)
                .setUpdateCurrent(true)
                .build()
                .schedule();
    }

    public static boolean checkStarted() {
        return !JobManager.instance().getAllJobsForTag(UpdateWeatherJob.TAG).isEmpty();
    }

    @Override
    @NonNull
    protected Result onRunJob(Params params) {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(getContext()))
                .build();
        appComponent.inject(this);

        WeatherData weather = weatherService
                .getCurrentWeather(Locale.getDefault().getLanguage())
                .subscribeOn(AndroidSchedulers.mainThread())
                .onErrorReturn(throwable -> null)
                .blockingGet();

        if (weather == null) {
            return Result.FAILURE;
        }

        weatherStorage.updateLastWeather(weather);

        return Result.SUCCESS;
    }
}