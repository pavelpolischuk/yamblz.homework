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

import static java.util.Collections.emptyList;

/**
 * Created by Kim Michael on 03.08.17
 */
public class CitySummariesAdapter extends RecyclerView.Adapter<CitySummariesAdapter.ViewHolder> {
    private List<FilteredCity> citySummaries = emptyList();
    private LayoutInflater layoutInflater;
    private OnCityClickListener onCityClickListener;
    private int selectedPosition;

    public CitySummariesAdapter(@NonNull LayoutInflater layoutInflater, OnCityClickListener onCityClickListener) {
        this.layoutInflater = layoutInflater;
        this.citySummaries = new ArrayList<>();
        this.onCityClickListener = onCityClickListener;
    }

    // Returns inserted position
    // -1 if not inserted
    public Long insert(@NonNull FilteredCity filteredCity) {
        if (!citySummaries.contains(filteredCity)) {
            citySummaries.add(0, filteredCity);
            notifyItemInserted(0);
            return (long) (citySummaries.size());
        }
        return -1L;
    }

    public void insertAll(List<FilteredCity> filteredCities) {
        citySummaries.clear();
        citySummaries.addAll(filteredCities);
        notifyDataSetChanged();
    }

    public void remove(int position) {
        citySummaries.remove(position);
        notifyItemRemoved(position);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = layoutInflater.inflate(R.layout.item_city_summary, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        v.setOnClickListener(v1 -> {
            int position = viewHolder.getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                int prev = selectedPosition;
                selectedPosition = position;
                notifyItemChanged(prev);
                v.setSelected(true);
                onCityClickListener.onCityClick(citySummaries.get(position));
                notifyItemChanged(selectedPosition);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemView.setSelected(position == selectedPosition);
        FilteredCity filteredCity = citySummaries.get(position);
        holder.bind(filteredCity);
    }

    @Override
    public int getItemCount() {
        return citySummaries.size();
    }

    public interface OnCityClickListener {
        void onCityClick(FilteredCity filteredCity);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.city_name)
        TextView cityName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(FilteredCity filteredCity) {
            cityName.setText(filteredCity.getCityName());
        }
    }
}
