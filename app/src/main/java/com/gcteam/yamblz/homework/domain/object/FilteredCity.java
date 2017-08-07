package com.gcteam.yamblz.homework.domain.object;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Kim Michael on 03.08.17
 */
public class FilteredCity implements Parcelable {
    private final String cityName;
    private final String countryName;
    private final String placeId;

    public FilteredCity(String cityName, String countryName, String placeId) {
        this.cityName = cityName;
        this.countryName = countryName;
        this.placeId = placeId;
    }

    protected FilteredCity(Parcel in) {
        this.cityName = in.readString();
        this.countryName = in.readString();
        this.placeId = in.readString();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.cityName);
        dest.writeString(this.countryName);
        dest.writeString(this.placeId);
    }

    public static final Parcelable.Creator<FilteredCity> CREATOR = new Parcelable.Creator<FilteredCity>() {
        @Override
        public FilteredCity createFromParcel(Parcel source) {
            return new FilteredCity(source);
        }

        @Override
        public FilteredCity[] newArray(int size) {
            return new FilteredCity[size];
        }
    };
}
