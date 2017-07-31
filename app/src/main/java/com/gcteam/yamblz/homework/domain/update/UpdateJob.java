package com.gcteam.yamblz.homework.domain.update;

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
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Created by turist on 16.07.2017.
 */

public class UpdateJob extends Job {

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
                .doOnSuccess(new Consumer<WeatherData>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull WeatherData weather) throws Exception {
                        Picasso.with(getContext()).load(weather.getIconUri()).fetch();
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .onErrorReturn(new Function<Throwable, WeatherData>() {
                    @Override
                    public WeatherData apply(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
                        return null;
                    }
                })
                .blockingGet();

        if(weather == null) {
            return Result.FAILURE;
        }

        weatherStorage.updateLastWeather(weather);

        return Result.SUCCESS;
    }

    public static void startUpdate(int minutesInterval) {
        new JobRequest.Builder(UpdateJob.TAG)
                .setPeriodic(TimeUnit.MINUTES.toMillis(minutesInterval), TimeUnit.MINUTES.toMillis(15))
                .setRequiredNetworkType(JobRequest.NetworkType.CONNECTED)
                .setRequirementsEnforced(true)
                .setPersisted(true)
                .setUpdateCurrent(true)
                .build()
                .schedule();
    }

    public static boolean checkStarted() {
        return !JobManager.instance().getAllJobsForTag(UpdateJob.TAG).isEmpty();
    }
}