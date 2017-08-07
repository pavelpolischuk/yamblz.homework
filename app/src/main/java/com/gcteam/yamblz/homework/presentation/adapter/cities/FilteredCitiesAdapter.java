package com.gcteam.yamblz.homework.presentation.adapter.cities;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gcteam.yamblz.homework.R;
import com.gcteam.yamblz.homework.domain.object.FilteredCity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Kim Michael on 03.08.17
 */
public class FilteredCitiesAdapter extends RecyclerView.Adapter<FilteredCitiesAdapter.ViewHolder> {

    private List<FilteredCity> filteredCities;
    private LayoutInflater layoutInflater;
    private OnCityClickListener listener;

    public FilteredCitiesAdapter(@NonNull LayoutInflater layoutInflater,
                                 OnCityClickListener onCityClickListener) {
        this.layoutInflater = layoutInflater;
        this.filteredCities = new ArrayList<>();
        this.listener = onCityClickListener;
    }

    public void insertAll(List<FilteredCity> filteredCities) {
        this.filteredCities.clear();
        this.filteredCities.addAll(filteredCities);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View v = layoutInflater.inflate(R.layout.item_filtered_city, parent, false);
        final ViewHolder viewHolder = new ViewHolder(v);
        v.setOnClickListener(v1 -> {
            if (viewHolder.getAdapterPosition() != RecyclerView.NO_POSITION) {
                listener.onCityClick(filteredCities.get(viewHolder.getAdapterPosition()));
            }
        });
        return viewHolder;
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

    public void clear() {
        filteredCities.clear();
        notifyDataSetChanged();
    }

    public interface OnCityClickListener {
        void onCityClick(FilteredCity filteredCity);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.city_name)
        TextView cityName;
        @BindView(R.id.country_name)
        TextView countryName;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(FilteredCity filteredCity) {
            this.cityName.setText(filteredCity.getCityName());
            this.countryName.setText(filteredCity.getCountryName());
        }
    }
}
