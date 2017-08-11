package com.gcteam.yamblz.homework.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.gcteam.yamblz.homework.data.local.dao.ChosenCityDAO;
import com.gcteam.yamblz.homework.data.local.dao.FullWeatherReportDAO;
import com.gcteam.yamblz.homework.data.object.StoredCity;
import com.gcteam.yamblz.homework.data.object.StoredFullWeatherReport;

/**
 * Created by Kim Michael on 09.08.17
 */
@Database(entities = {StoredCity.class, StoredFullWeatherReport.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ChosenCityDAO chosenCityDAO();

    public abstract FullWeatherReportDAO fullWeatherReportDAO();

}
