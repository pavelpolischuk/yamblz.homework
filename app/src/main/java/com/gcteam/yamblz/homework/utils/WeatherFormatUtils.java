package com.gcteam.yamblz.homework.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.DrawableRes;

import com.gcteam.yamblz.homework.R;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by Kim Michael on 08.08.17
 */
public class WeatherFormatUtils {

    public static final String DATE_FORMAT = "dd MMMM";
    public static final String DAY_FORMAT = "EEEE";

    @DrawableRes
    public static int getArtResourceForWeatherCondition(int weatherId) {
        // Based on weather code data found at:
        // http://bugs.openweathermap.org/projects/api/wiki/Weather_Condition_Codes
        if (weatherId >= 200 && weatherId <= 232) {
            return R.drawable.art_storm;
        } else if (weatherId >= 300 && weatherId <= 321) {
            return R.drawable.art_light_rain;
        } else if (weatherId >= 500 && weatherId <= 504) {
            return R.drawable.art_rain;
        } else if (weatherId == 511) {
            return R.drawable.art_snow;
        } else if (weatherId >= 520 && weatherId <= 531) {
            return R.drawable.art_rain;
        } else if (weatherId >= 600 && weatherId <= 622) {
            return R.drawable.art_snow;
        } else if (weatherId >= 701 && weatherId <= 761) {
            return R.drawable.art_fog;
        } else if (weatherId == 761 || weatherId == 781) {
            return R.drawable.art_storm;
        } else if (weatherId == 800) {
            return R.drawable.art_clear;
        } else if (weatherId == 801) {
            return R.drawable.art_light_clouds;
        } else if (weatherId >= 802 && weatherId <= 804) {
            return R.drawable.art_clouds;
        }
        return -1;
    }

    @DrawableRes
    public static int getIconResourceForWeatherCondition(int weatherId) {
        // Based on weather code data found at:
        // http://bugs.openweathermap.org/projects/api/wiki/Weather_Condition_Codes
        if (weatherId >= 200 && weatherId <= 232) {
            return R.drawable.ic_storm;
        } else if (weatherId >= 300 && weatherId <= 321) {
            return R.drawable.ic_light_rain;
        } else if (weatherId >= 500 && weatherId <= 504) {
            return R.drawable.ic_rain;
        } else if (weatherId == 511) {
            return R.drawable.ic_snow;
        } else if (weatherId >= 520 && weatherId <= 531) {
            return R.drawable.ic_rain;
        } else if (weatherId >= 600 && weatherId <= 622) {
            return R.drawable.ic_snow;
        } else if (weatherId >= 701 && weatherId <= 761) {
            return R.drawable.ic_fog;
        } else if (weatherId == 761 || weatherId == 781) {
            return R.drawable.ic_storm;
        } else if (weatherId == 800) {
            return R.drawable.ic_clear;
        } else if (weatherId == 801) {
            return R.drawable.ic_light_clouds;
        } else if (weatherId >= 802 && weatherId <= 804) {
            return R.drawable.ic_cloudy;
        }
        return -1;
    }

    public static String formatTemperature(Context context, double temperature) {
        if (!isMetric(context)) {
            temperature = (temperature * 1.8) + 32;
        }

        return String.format(context.getString(R.string.format_temperature), temperature);
    }

    public static boolean isMetric(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString(PreferencesManager.UNITS_KEY,
                context.getString(R.string.units_metric_value))
                .equals(context.getString(R.string.units_metric_value));
    }

    public static String getFormattedWind(Context context, double windSpeed, double degrees) {
        int windFormat;
        if (isMetric(context)) {
            windFormat = R.string.format_wind_kmh;
        } else {
            windFormat = R.string.format_wind_mph;
            windSpeed = .621371192237334f * windSpeed;
        }

        String direction = "Unknown";
        if (degrees >= 337.5 || degrees < 22.5) {
            direction = "N";
        } else if (degrees >= 22.5 && degrees < 67.5) {
            direction = "NE";
        } else if (degrees >= 67.5 && degrees < 112.5) {
            direction = "E";
        } else if (degrees >= 112.5 && degrees < 157.5) {
            direction = "SE";
        } else if (degrees >= 157.5 && degrees < 202.5) {
            direction = "S";
        } else if (degrees >= 202.5 && degrees < 247.5) {
            direction = "SW";
        } else if (degrees >= 247.5 && degrees < 292.5) {
            direction = "W";
        } else if (degrees >= 292.5 && degrees < 337.5) {
            direction = "NW";
        }
        return String.format(context.getString(windFormat), windSpeed, direction);
    }

    public static String getFormattedDate(long dateInMillis) {
        SimpleDateFormat monthDayFormat = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());
        String monthDayString = monthDayFormat.format(dateInMillis * 1000);
        return monthDayString.substring(0, 1).toUpperCase() + monthDayString.substring(1);
    }

    public static String getFormattedDay(long dateInMillis) {
        SimpleDateFormat monthDayFormat = new SimpleDateFormat(DAY_FORMAT, Locale.getDefault());
        String monthDayString = monthDayFormat.format(dateInMillis * 1000);
        return monthDayString.substring(0, 1).toUpperCase() + monthDayString.substring(1);
    }

    public static String getFormattedDayAndDate(long dateInMillis) {
        return getFormattedDay(dateInMillis) + ", " + getFormattedDate(dateInMillis);
    }

    public static String formatDescription(String description) {
        return description.substring(0, 1).toUpperCase() + description.substring(1);
    }
}
