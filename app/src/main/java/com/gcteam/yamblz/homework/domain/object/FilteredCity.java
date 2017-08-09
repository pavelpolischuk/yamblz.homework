package com.gcteam.yamblz.homework.domain.object;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Kim Michael on 03.08.17
 */
public class FilteredCity implements Parcelable {
    public static final Creator<FilteredCity> CREATOR = new Creator<FilteredCity>() {
        @Override
        public FilteredCity createFromParcel(Parcel source) {
            return new FilteredCity(source);
        }

        @Override
        public FilteredCity[] newArray(int size) {
            return new FilteredCity[size];
        }
    };
    private final String cityName;
    private final String countryName;
    private final String placeId;
    @Nullable
    private Long id;

    public FilteredCity(@NonNull String cityName,
                        @NonNull String countryName,
                        @NonNull String placeId,
                        @NonNull Long id) {
        this.cityName = cityName;
        this.countryName = countryName;
        this.placeId = placeId;
        this.id = id;
    }

    public FilteredCity(String cityName, String countryName, String placeId) {
        this.cityName = cityName;
        this.countryName = countryName;
        this.placeId = placeId;
        this.id = 0L;
    }

    public FilteredCity(Parcel in) {
        this.cityName = in.readString();
        this.countryName = in.readString();
        this.placeId = in.readString();
        this.id = (Long) in.readValue(Long.class.getClassLoader());
    }

    public String getCityName() {
        return cityName;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getPlaceId() {
        return placeId;
    }

    public Long getId() {
        return id;
    }

    public void setId(@NonNull Long id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.cityName);
        dest.writeString(this.countryName);
        dest.writeString(this.placeId);
        dest.writeValue(this.id);
    }
}
