package com.gcteam.yamblz.homework.presentation.adapter.cities;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gcteam.yamblz.homework.R;
import com.gcteam.yamblz.homework.domain.object.ChosenCity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static java.util.Collections.emptyList;

/**
 * Created by Kim Michael on 03.08.17
 */
public class CitySummariesAdapter extends RecyclerView.Adapter<CitySummariesAdapter.ViewHolder> {
    private List<ChosenCity> citySummaries = emptyList();
    private LayoutInflater layoutInflater;
    private OnCityClickListener onCityClickListener;
    private int selectedPosition;

    public CitySummariesAdapter(@NonNull LayoutInflater layoutInflater, OnCityClickListener onCityClickListener) {
        this.layoutInflater = layoutInflater;
        this.citySummaries = new ArrayList<>();
        this.onCityClickListener = onCityClickListener;
    }

    public boolean insert(@NonNull ChosenCity chosenCity) {
        if (!citySummaries.contains(chosenCity)) {
            citySummaries.add(chosenCity);
            notifyItemInserted(citySummaries.size() - 1);
            return true;
        }
        return false;
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
        ChosenCity chosenCity = citySummaries.get(position);
        holder.bind(chosenCity);
    }

    @Override
    public int getItemCount() {
        return citySummaries.size();
    }

    public interface OnCityClickListener {
        void onCityClick(ChosenCity chosenCity);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.city_name)
        TextView cityName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(ChosenCity chosenCity) {
            cityName.setText(chosenCity.getCityName());
        }
    }
}
