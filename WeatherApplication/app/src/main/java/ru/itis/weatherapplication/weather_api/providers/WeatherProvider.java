package ru.itis.weatherapplication.weather_api.providers;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.google.gson.Gson;

import java.util.LinkedList;

import ru.itis.weatherapplication.weather_api.entities.one_day.List;
import ru.itis.weatherapplication.weather_api.entities.one_day.MyWeather;
import ru.itis.weatherapplication.weather_api.exceptions.NotLoadedException;

/**
 * Created by ilmaz on 02.11.16.
 */
public class WeatherProvider {
    private static final String PREFERENCE_NAME = "weatherApplication";
    private static final String PREFERENCE_ONE_DAY_WEATHER = "oneDayWeather";
    private static WeatherProvider ourInstance;

    private WeatherProvider() {
    }

    public static WeatherProvider getInstance() {
        if (ourInstance == null) {
            ourInstance = new WeatherProvider();
        }
        return ourInstance;
    }

    public void saveOneDayWeather(@NonNull Context context, @NonNull MyWeather weather) throws NotLoadedException {
        if (weather.isIncludeError()) {
            throw weather.getNotLoadedException();
        }
        SharedPreferences preferences = context.getApplicationContext().getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        Gson gson = new Gson();
        String jsonString = gson.toJson(weather);

        editor.putString(PREFERENCE_ONE_DAY_WEATHER, jsonString);
        editor.apply();
    }

    public MyWeather getOneDayWeather(@NonNull Context context) {
        SharedPreferences preferences = context.getApplicationContext().getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        String jsonString = preferences.getString(PREFERENCE_ONE_DAY_WEATHER, "");

        Gson gson = new Gson();
        MyWeather weather;
        weather = gson.fromJson(jsonString, MyWeather.class);
        if (weather == null) {
            weather = new MyWeather(new LinkedList<List>());
        }
        return weather;
    }
}
