package com.gcteam.yamblz.homework.domain.update.weather;

import android.support.annotation.NonNull;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobManager;
import com.evernote.android.job.JobRequest;
import com.gcteam.yamblz.homework.presentation.di.component.DaggerAppComponent;
import com.gcteam.yamblz.homework.presentation.di.module.AppModule;
import com.gcteam.yamblz.homework.data.network.WeatherService;
import com.gcteam.yamblz.homework.data.local.WeatherStorage;
import com.gcteam.yamblz.homework.data.WeatherData;
import com.squareup.picasso.Picasso;

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

    @Override
    @NonNull
    protected Result onRunJob(Params params) {
        DaggerAppComponent.builder().appModule(new AppModule(getContext())).build().inject(this);

        WeatherData weather = weatherService
                .currentWeather(Locale.getDefault().getLanguage())
                .doOnSuccess(weather1 -> Picasso.with(getContext()).load(weather1.getIconUri()).fetch())
                .subscribeOn(AndroidSchedulers.mainThread())
                .onErrorReturn(throwable -> null)
                .blockingGet();

        if(weather == null) {
            return Result.FAILURE;
        }

        weatherStorage.updateLastWeather(weather);

        return Result.SUCCESS;
    }

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
}