package com.gcteam.yamblz.homework.presentation.adapter.cities;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gcteam.yamblz.homework.R;
import com.gcteam.yamblz.homework.domain.object.FilteredCity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.support.v7.widget.RecyclerView.NO_POSITION;
import static java.util.Collections.emptyList;

/**
 * Created by Kim Michael on 03.08.17
 */
public class CitySummariesAdapter extends RecyclerView.Adapter<CitySummariesAdapter.ViewHolder> {
    private List<FilteredCity> citySummaries = emptyList();
    private LayoutInflater layoutInflater;
    private OnCityClickListener onCityClickListener;
    private int selectedPosition;
    private boolean isDeleteButtonShown;

    public CitySummariesAdapter(@NonNull LayoutInflater layoutInflater,
                                OnCityClickListener onCityClickListener,
                                int chosenCityId) {
        this.layoutInflater = layoutInflater;
        this.citySummaries = new ArrayList<>();
        this.onCityClickListener = onCityClickListener;
        if (chosenCityId != 0) {
            selectedPosition = chosenCityId;
        }
        isDeleteButtonShown = false;
    }

    // Returns inserted position
    // -1 if not inserted
    public int insert(@NonNull FilteredCity filteredCity) {
        if (!citySummaries.contains(filteredCity)) {
            citySummaries.add(0, filteredCity);
            notifyItemInserted(0);
            int prev = selectedPosition;
            selectedPosition = 0;
            notifyItemChanged(prev + 1);
            onCityClickListener.onCityClick(filteredCity);
            return citySummaries.size() - 1;
        }
        return -1;
    }

    public void insertAll(List<FilteredCity> filteredCities) {
        citySummaries.clear();
        citySummaries.addAll(filteredCities);
        notifyDataSetChanged();
    }

    private void remove(int position) {
        int initialSize = citySummaries.size();
        FilteredCity deletedCity = citySummaries.get(position);
        citySummaries.remove(position);
        // If there is only one city left, delete it
        if (initialSize == 1) {
            notifyItemRemoved(0);
            return;
            // If we delete last city and it's chosen, make last city before it selected
        } else if (position == initialSize - 1 && position == selectedPosition) {
            selectedPosition--;
            notifyItemRemoved(position);
            notifyItemChanged(selectedPosition);
            // If we deleted city over selected, every city under will change
        } else if (selectedPosition > position) {
            selectedPosition--;
            notifyItemRangeChanged(position, citySummaries.size() - position);
        } else {
            notifyDataSetChanged();
        }
        onCityClickListener.onDeleteCityClick(deletedCity);
    }

    public void switchIsDeletedButtonShown() {
        changeIsDeletedButtonShown(!this.isDeleteButtonShown);
    }

    public void changeIsDeletedButtonShown(boolean isDeleteButtonShown) {
        if (this.isDeleteButtonShown != isDeleteButtonShown) {
            this.isDeleteButtonShown = isDeleteButtonShown;
            notifyDataSetChanged();
        }
    }

    private void changeSelection(int position, View view) {
        int prev = selectedPosition;
        selectedPosition = position;
        notifyItemChanged(prev);
        view.setSelected(true);
        onCityClickListener.onCityClick(citySummaries.get(position));
        notifyItemChanged(selectedPosition);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = layoutInflater.inflate(R.layout.item_city_summary, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        v.setOnClickListener(v1 -> {
            int position = viewHolder.getAdapterPosition();
            if (position != NO_POSITION) {
                changeSelection(position, v);
            }
        });
        viewHolder.deleteButton.setOnClickListener(v12 -> {
            int position = viewHolder.getAdapterPosition();
            if (position != NO_POSITION) {
                FilteredCity deletedCity = citySummaries.get(position);
                remove(position);
                onCityClickListener.onDeleteCityClick(deletedCity);
                notifyItemRemoved(position);
            }
        });
        return viewHolder;
    }

    public boolean isEmpty() {
        return citySummaries.isEmpty();
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemView.setSelected(position == selectedPosition);
        FilteredCity filteredCity = citySummaries.get(position);
        holder.bind(filteredCity, this.isDeleteButtonShown);
    }

    @Override
    public int getItemCount() {
        return citySummaries.size();
    }

    public void setSelected(int chosenCityId) {
        selectedPosition = chosenCityId;
        notifyItemChanged(selectedPosition);
    }

    public FilteredCity getSelectedCity() {
        return citySummaries.get(selectedPosition);
    }


    public interface OnCityClickListener {
        void onCityClick(FilteredCity filteredCity);
        void onDeleteCityClick(FilteredCity filteredCity);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.city_name)
        TextView cityName;
        @BindView(R.id.delete_city_button)
        ImageView deleteButton;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(FilteredCity filteredCity, boolean isDeleteButtonShown) {
            cityName.setText(filteredCity.getCityName());
            deleteButton.setVisibility(isDeleteButtonShown ? View.VISIBLE : View.GONE);
        }
    }
}