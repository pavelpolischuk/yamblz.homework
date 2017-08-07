package com.gcteam.yamblz.homework.domain.object;

/**
 * Created by Kim Michael on 03.08.17
 */
public class ChosenCity {

    private String cityName;

    public ChosenCity(String cityName) {
        this.cityName = cityName;
    }

    public static ChosenCity from(FilteredCity chosenCity) {
        return new ChosenCity(chosenCity.getCityName());
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChosenCity that = (ChosenCity) o;

        return cityName != null ? cityName.equals(that.cityName) : that.cityName == null;

    }

    @Override
    public int hashCode() {
        return cityName != null ? cityName.hashCode() : 0;
    }
}
