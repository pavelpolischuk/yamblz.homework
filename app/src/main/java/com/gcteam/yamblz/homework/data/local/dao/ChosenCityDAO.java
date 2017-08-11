package com.gcteam.yamblz.homework.data.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.gcteam.yamblz.homework.data.object.StoredCity;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by Kim Michael on 09.08.17
 */
@Dao
public interface ChosenCityDAO {

    @Query("SELECT * FROM StoredCity ORDER BY priority")
    Single<List<StoredCity>> getAll();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(StoredCity storedCity);

    @Query("SELECT * FROM StoredCity WHERE placeId = :placeId")
    Single<StoredCity> get(String placeId);

    @Query("DELETE FROM StoredCity WHERE placeId = :placeId")
    int deleteCity(String placeId);
}
