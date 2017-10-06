package com.gcteam.yamblz.homework.domain.update.weather;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobCreator;

/**
 * Created by turist on 16.07.2017.
 */

public class WeatherJobCreator implements JobCreator {

    @Override
    public Job create(String tag) {
        switch (tag) {
            case UpdateWeatherJob.TAG:
                return new UpdateWeatherJob();
            default:
                return null;
        }
    }
}
