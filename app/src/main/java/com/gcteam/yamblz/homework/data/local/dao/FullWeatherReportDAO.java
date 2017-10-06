package com.gcteam.yamblz.homework.data.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.gcteam.yamblz.homework.data.object.StoredFullWeatherReport;

import io.reactivex.Single;

/**
 * Created by Kim Michael on 09.08.17
 */
@Dao
public interface FullWeatherReportDAO {

    @Query("SELECT * FROM StoredFullWeatherReport WHERE lat = :lat AND lng = :lng")
    Single<StoredFullWeatherReport> get(double lat, double lng);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(StoredFullWeatherReport storedFullWeatherReport);

    @Query("DELETE FROM StoredFullWeatherReport WHERE lat = :lat AND lng = :lng")
    int deleteCity(double lat, double lng);
}
