package com.gcteam.yamblz.homework.weather.updating;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobManager;
import com.evernote.android.job.JobRequest;
import com.gcteam.yamblz.homework.weather.WeatherService;
import com.gcteam.yamblz.homework.weather.WeatherStorage;
import com.gcteam.yamblz.homework.weather.api.Weather;
import com.squareup.picasso.Picasso;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

/**
 * Created by turist on 16.07.2017.
 */

public class UpdateJob extends Job {

    public static final String TAG = "current_weather_update_job";

    private static class ErrorConsumer implements Consumer<Throwable> {

        boolean hasError = false;

        @Override
        public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
            hasError = true;
        }
    }

    @Override
    @NonNull
    protected Result onRunJob(Params params) {

        if (Build.VERSION.SDK_INT >= 23 &&
                ContextCompat.checkSelfPermission(getContext(), Manifest.permission.INTERNET )
                        != PackageManager.PERMISSION_GRANTED) {
            return Result.SUCCESS;
        }

        ErrorConsumer errorConsumer = new ErrorConsumer();
        WeatherService.get()
                .currentWeather(Locale.getDefault().getLanguage())
                .doOnNext(new Consumer<Weather>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull Weather weather) throws Exception {
                        Picasso.with(getContext()).load(weather.getIconUri()).fetch();
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .blockingSubscribe(WeatherStorage.get().updateLastWeather(), errorConsumer);

        return errorConsumer.hasError ? Result.FAILURE : Result.SUCCESS;
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