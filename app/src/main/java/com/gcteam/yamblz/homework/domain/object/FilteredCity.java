package com.gcteam.yamblz.homework.domain.object;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Kim Michael on 03.08.17
 */
public class FilteredCity implements Parcelable {

    @NonNull
    private final String cityName;
    @NonNull
    private final String countryName;
    @NonNull
    private final String placeId;
    @Nullable
    private Integer priority;

    public FilteredCity(@NonNull String cityName,
                        @NonNull String countryName,
                        @NonNull String placeId,
                        @Nullable Integer priority) {
        this.cityName = cityName;
        this.countryName = countryName;
        this.placeId = placeId;
        this.priority = priority;
    }

    public FilteredCity(@NonNull String cityName,
                        @NonNull String countryName,
                        @NonNull String placeId) {
        this.cityName = cityName;
        this.countryName = countryName;
        this.placeId = placeId;
        this.priority = 0;
    }

    @NonNull
    public String getCityName() {
        return cityName;
    }

    @NonNull
    public String getCountryName() {
        return countryName;
    }

    @NonNull
    public String getPlaceId() {
        return placeId;
    }

    @Nullable
    public Integer getPriority() {
        return priority;
    }

    public void setPriority(@Nullable Integer priority) {
        this.priority = priority;
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
        dest.writeValue(this.priority);
    }

    protected FilteredCity(Parcel in) {
        this.cityName = in.readString();
        this.countryName = in.readString();
        this.placeId = in.readString();
        this.priority = (Integer) in.readValue(Integer.class.getClassLoader());
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

        if (!cityName.equals(that.cityName)) return false;
        if (!countryName.equals(that.countryName)) return false;
        return placeId.equals(that.placeId)
                && (priority != null ? priority.equals(that.priority) : that.priority == null);

    }

    @Override
    public int hashCode() {
        int result = cityName.hashCode();
        result = 31 * result + countryName.hashCode();
        result = 31 * result + placeId.hashCode();
        result = 31 * result + (priority != null ? priority.hashCode() : 0);
        return result;
    }
}
