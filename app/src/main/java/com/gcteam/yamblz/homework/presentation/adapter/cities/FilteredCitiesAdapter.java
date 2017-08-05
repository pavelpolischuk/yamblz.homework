package com.gcteam.yamblz.homework.presentation.adapter.cities;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gcteam.yamblz.homework.R;
import com.gcteam.yamblz.homework.domain.object.FilteredCity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Kim Michael on 03.08.17
 */
public class FilteredCitiesAdapter extends RecyclerView.Adapter<FilteredCitiesAdapter.ViewHolder> {

    private List<FilteredCity> filteredCities;
    private LayoutInflater layoutInflater;

    public FilteredCitiesAdapter(@NonNull LayoutInflater layoutInflater) {
        this.layoutInflater = layoutInflater;
    }

    public void insertAll(List<FilteredCity> filteredCities) {
        this.filteredCities = filteredCities;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = layoutInflater.inflate(R.layout.item_filtered_city, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        FilteredCity filteredCity = filteredCities.get(position);
        holder.bind(filteredCity);
    }

    @Override
    public int getItemCount() {
        return filteredCities.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.city_name)
        TextView cityName;
        @BindView(R.id.country_name)
        TextView countryName;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(itemView);
        }

        void bind(FilteredCity filteredCity) {
            this.cityName.setText(filteredCity.getCityName());
            this.countryName.setText(filteredCity.getCountryName());
        }
    }
}
