package com.gcteam.yamblz.homework.presentation.adapter.weather;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gcteam.yamblz.homework.R;
import com.gcteam.yamblz.homework.domain.object.FullWeatherReport;
import com.gcteam.yamblz.homework.domain.object.WeatherData;
import com.gcteam.yamblz.homework.utils.WeatherFormatUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Kim Michael on 08.08.17
 */
public class FullWeatherAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int CURRENT_WEATHER_VIEW_TYPE = 0;
    private static final int FORECAST_VIEW_TYPE = 1;

    private FullWeatherReport fullWeatherReport;
    private final OnForecastClickListener onForecastClickListener;

    public FullWeatherAdapter(OnForecastClickListener onForecastClickListener) {
        this.fullWeatherReport = null;
        this.onForecastClickListener = onForecastClickListener;
    }

    @Override
    public long getItemId(int position) {
        switch (position) {
            case 0:
                return fullWeatherReport.getWeatherData().hashCode();
            default:
                return fullWeatherReport.getForecastData().getForecast().get(position - 1).hashCode();
        }
    }

    public void insertFullWeather(FullWeatherReport fullWeatherReport) {
        this.fullWeatherReport = fullWeatherReport;
        this.notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v;
        RecyclerView.ViewHolder viewHolder;
        switch (viewType) {
            case CURRENT_WEATHER_VIEW_TYPE:
                v = layoutInflater.inflate(R.layout.item_current_weather, parent, false);
                viewHolder = new CurrentWeatherViewHolder(v);
                break;
            case FORECAST_VIEW_TYPE:
            default:
                v = layoutInflater.inflate(R.layout.item_forecast_entry, parent, false);
                viewHolder = new ForecastViewHolder(v);
                v.setOnClickListener(v1 -> {
                    int position = viewHolder.getAdapterPosition() - 1;
                    if (position != RecyclerView.NO_POSITION) {
                        onForecastClickListener.onForecastClick(fullWeatherReport
                                .getForecastData()
                                .getForecast()
                                .get(position));
                    }
                });
        }
        return viewHolder;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return CURRENT_WEATHER_VIEW_TYPE;
            default:
                return FORECAST_VIEW_TYPE;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case CURRENT_WEATHER_VIEW_TYPE:
                CurrentWeatherViewHolder currentWeatherViewHolder = (CurrentWeatherViewHolder) holder;
                currentWeatherViewHolder.bind(fullWeatherReport.getWeatherData());
                break;
            case FORECAST_VIEW_TYPE:
                ForecastViewHolder forecastViewHolder = (ForecastViewHolder) holder;
                forecastViewHolder.bind(fullWeatherReport.getForecastData().getForecast().get(position - 1));
                break;
        }
    }

    @Override
    public int getItemCount() {
        if (fullWeatherReport == null)
            return 0;
        return fullWeatherReport.getForecastData().getForecast().size() + 1;
    }

    public static class ForecastViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.weather_icon)
        ImageView weatherIcon;
        @BindView(R.id.day)
        TextView day;
        @BindView(R.id.max_temp)
        TextView maxTemp;
        @BindView(R.id.min_temp)
        TextView minTemp;

        private Context context;

        public ForecastViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.context = itemView.getContext();
        }

        void bind(WeatherData weatherData) {
            weatherIcon.setImageDrawable(ContextCompat.getDrawable(context,
                    WeatherFormatUtils.getIconResourceForWeatherCondition(weatherData.getWeatherId())));
            day.setText(WeatherFormatUtils.getFormattedDayAndDate(weatherData.getDate()));
            maxTemp.setText(WeatherFormatUtils.formatTemperature(context, weatherData.getMaxTemp()));
            minTemp.setText(WeatherFormatUtils.formatTemperature(context, weatherData.getMinTemp()));
        }
    }

    public static class CurrentWeatherViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.weather_icon)
        ImageView weatherIcon;
        @BindView(R.id.max_temp)
        TextView maxTemp;
        @BindView(R.id.min_temp)
        TextView minTemp;
        @BindView(R.id.wind)
        TextView wind;
        @BindView(R.id.humidity)
        TextView humidity;
        @BindView(R.id.pressure)
        TextView pressure;
        @BindView(R.id.description)
        TextView description;
        private Context context;

        public CurrentWeatherViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            context = itemView.getContext();
        }

        void bind(WeatherData weatherData) {
            weatherIcon.setImageDrawable(ContextCompat.getDrawable(context,
                    (WeatherFormatUtils.getArtResourceForWeatherCondition(
                            weatherData.getWeatherId()))));
            maxTemp.setText(WeatherFormatUtils.formatTemperature(context, weatherData.getMaxTemp()));
            minTemp.setText(WeatherFormatUtils.formatTemperature(context, weatherData.getMinTemp()));
            wind.setText(WeatherFormatUtils.getFormattedWind(context, weatherData.getWindSpeed(), weatherData.getWindDeg()));
            humidity.setText(String.format(context.getString(R.string.format_humidity), weatherData.getHumidity()));
            pressure.setText(String.format(context.getString(R.string.format_pressure), weatherData.getPressure()));
            description.setText(WeatherFormatUtils.formatDescription(weatherData.getDescription()));
        }
    }

    public interface OnForecastClickListener {
        void onForecastClick(WeatherData weatherData);
    }
}
