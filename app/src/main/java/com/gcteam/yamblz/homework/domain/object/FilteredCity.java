package com.gcteam.yamblz.homework.domain.object;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Kim Michael on 03.08.17
 */
public class FilteredCity implements Parcelable {
    private final String cityName;
    private final String countryName;
    private final String placeId;
    @Nullable
    private Integer id;
    public FilteredCity(@NonNull String cityName,
                        @NonNull String countryName,
                        @NonNull String placeId,
                        @NonNull Integer id) {
        this.cityName = cityName;
        this.countryName = countryName;
        this.placeId = placeId;
        this.id = id;
    }

    public FilteredCity(String cityName, String countryName, String placeId) {
        this.cityName = cityName;
        this.countryName = countryName;
        this.placeId = placeId;
        this.id = 0;
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

    @Nullable
    public Integer getId() {
        return id;
    }

    public void setId(@Nullable Integer id) {
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

    protected FilteredCity(Parcel in) {
        this.cityName = in.readString();
        this.countryName = in.readString();
        this.placeId = in.readString();
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FilteredCity that = (FilteredCity) o;

        if (cityName != null ? !cityName.equals(that.cityName) : that.cityName != null)
            return false;
        if (countryName != null ? !countryName.equals(that.countryName) : that.countryName != null)
            return false;
        return placeId != null ? placeId.equals(that.placeId) : that.placeId == null;

    }

    @Override
    public int hashCode() {
        int result = cityName != null ? cityName.hashCode() : 0;
        result = 31 * result + (countryName != null ? countryName.hashCode() : 0);
        result = 31 * result + (placeId != null ? placeId.hashCode() : 0);
        return result;
    }
}
