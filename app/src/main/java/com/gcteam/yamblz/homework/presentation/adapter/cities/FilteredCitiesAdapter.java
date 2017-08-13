package com.gcteam.yamblz.homework.presentation.adapter.cities;

import android.content.Context;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
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
    @NonNull
    private final LayoutInflater layoutInflater;
    private OnCityClickListener listener;

    private Context context;

    @MainThread
    public FilteredCitiesAdapter(@NonNull LayoutInflater layoutInflater,
                                 OnCityClickListener onCityClickListener,
                                 Context context) {
        this.layoutInflater = layoutInflater;
        this.filteredCities = new ArrayList<>();
        this.listener = onCityClickListener;
        this.context = context;
    }

    @MainThread
    public void insertAll(List<FilteredCity> filteredCities) {
        this.filteredCities.clear();
        this.filteredCities.addAll(filteredCities);
        notifyDataSetChanged();
    }

    @Override
    @MainThread
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View v = layoutInflater.inflate(R.layout.item_filtered_city, parent, false);
        final ViewHolder viewHolder = new ViewHolder(v, context);
        v.setOnClickListener(v1 -> {
            if (viewHolder.getAdapterPosition() != RecyclerView.NO_POSITION) {
                listener.onCityClick(filteredCities.get(viewHolder.getAdapterPosition()));
            }
        });
        return viewHolder;
    }

    @Override
    @MainThread
    public void onBindViewHolder(ViewHolder holder, int position) {
        FilteredCity filteredCity = filteredCities.get(position);
        holder.bind(filteredCity);
    }

    @Override
    @MainThread
    public int getItemCount() {
        return filteredCities.size();
    }

    @MainThread
    public void clear() {
        filteredCities.clear();
        notifyDataSetChanged();
    }

    public interface OnCityClickListener {
        @MainThread
        void onCityClick(FilteredCity filteredCity);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.city_name)
        TextView cityName;
        @BindView(R.id.country_name)
        TextView countryName;

        Context context;

        @MainThread
        ViewHolder(View itemView, Context context) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.context = context;
        }

        @MainThread
        void bind(@NonNull FilteredCity filteredCity) {
            this.cityName.setText(filteredCity.getCityName(), TextView.BufferType.SPANNABLE);
            if (!filteredCity.getCityName().isEmpty()) {
                Spannable spannable = (Spannable) cityName.getText();
                spannable.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, R.color.red)),
                        0, 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            }
            this.countryName.setText(filteredCity.getCountryName());
        }
    }
}
