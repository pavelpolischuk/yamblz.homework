package com.gcteam.yamblz.homework.presentation.adapter.cities;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gcteam.yamblz.homework.R;
import com.gcteam.yamblz.homework.domain.object.CitySummary;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static java.util.Collections.emptyList;

/**
 * Created by Kim Michael on 03.08.17
 */
public class CitySummariesAdapter extends RecyclerView.Adapter<CitySummariesAdapter.ViewHolder>{
    List<CitySummary> citySummaries = emptyList();
    LayoutInflater layoutInflater;

    public CitySummariesAdapter(LayoutInflater layoutInflater) {
        this.layoutInflater = layoutInflater;
        this.citySummaries = new ArrayList<>();
    }

    public void insertAll(List<CitySummary> citySummaries) {
        this.citySummaries = citySummaries;
    }

    public void insert(int position, CitySummary citySummary) {
        citySummaries.add(position, citySummary);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        citySummaries.remove(position);
        notifyItemRemoved(position);
    }

    public void removeAll() {
        citySummaries.clear();
        notifyDataSetChanged();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = layoutInflater.inflate(R.layout.item_city_summary, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CitySummary citySummary = citySummaries.get(position);
        holder.cityName.setText(citySummary.getCityName());
        holder.temperatureSummary.setText(citySummary.getTemperatureSummary());
    }

    @Override
    public int getItemCount() {
        return citySummaries.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.city_name)
        TextView cityName;
        @BindView(R.id.temperature_summary)
        TextView temperatureSummary;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(itemView);
        }
    }
}
