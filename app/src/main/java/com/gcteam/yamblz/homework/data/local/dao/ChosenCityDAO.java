package com.gcteam.yamblz.homework.data.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.gcteam.yamblz.homework.data.object.StoredChosenCity;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by Kim Michael on 09.08.17
 */
@Dao
public interface ChosenCityDAO {

    @Query("SELECT * FROM StoredChosenCity ORDER BY priority")
    Single<List<StoredChosenCity>> getAll();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(StoredChosenCity storedChosenCity);

    @Query("SELECT * FROM StoredChosenCity WHERE placeId = :placeId")
    Single<StoredChosenCity> get(String placeId);

    @Query("DELETE FROM StoredChosenCity WHERE placeId = :placeId")
    int deleteCity(String placeId);
}
