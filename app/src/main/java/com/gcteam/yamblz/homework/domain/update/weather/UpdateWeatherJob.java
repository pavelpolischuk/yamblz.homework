package com.gcteam.yamblz.homework.domain.update.weather;

import android.support.annotation.NonNull;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobManager;
import com.evernote.android.job.JobRequest;
import com.gcteam.yamblz.homework.domain.interactor.weather.WeatherInteractor;
import com.gcteam.yamblz.homework.domain.object.FullWeatherReport;
import com.gcteam.yamblz.homework.presentation.di.component.DaggerAppComponent;
import com.gcteam.yamblz.homework.presentation.di.component.WeatherComponent;
import com.gcteam.yamblz.homework.presentation.di.module.AppModule;
import com.gcteam.yamblz.homework.utils.PreferencesManager;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * Created by turist on 16.07.2017.
 */

public class UpdateWeatherJob extends Job {

    public static final String TAG = "current_weather_update_job";

    @Inject
    WeatherInteractor weatherInteractor;
    @Inject
    PreferencesManager preferencesManager;

    private WeatherComponent weatherComponent;

    public static boolean checkStarted() {
        return !JobManager.instance().getAllJobsForTag(UpdateWeatherJob.TAG).isEmpty();
    }

    public void startUpdate(int minutesInterval) {
        new JobRequest.Builder(UpdateWeatherJob.TAG)
                .setPeriodic(TimeUnit.MINUTES.toMillis(minutesInterval), TimeUnit.MINUTES.toMillis(15))
                .setRequiredNetworkType(JobRequest.NetworkType.CONNECTED)
                .setRequirementsEnforced(true)
                .setPersisted(true)
                .setUpdateCurrent(true)
                .build()
                .schedule();
    }

    @Override
    @NonNull
    protected Result onRunJob(Params params) {
        Timber.d("Running job on fetching forecast");

        weatherComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(getContext()))
                .build()
                .getWeatherComponent();
        weatherComponent.inject(this);

        FullWeatherReport fullWeatherReport =
                weatherInteractor
                        .getWeather(preferencesManager.getLat(), preferencesManager.getLng(),
                                true).onErrorReturn(throwable -> null).blockingGet();

        if (fullWeatherReport == null) {
            Timber.d("Failed updating forecast, rescheduling");
            return Result.RESCHEDULE;
        }

        Timber.d("Successfully updated forecast");
        return Result.SUCCESS;
    }
}