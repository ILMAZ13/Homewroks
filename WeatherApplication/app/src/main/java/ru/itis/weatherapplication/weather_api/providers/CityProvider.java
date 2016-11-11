package ru.itis.weatherapplication.weather_api.providers;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

import ru.itis.weatherapplication.weather_api.entities.one_day.City;

/**
 * Created by ilmaz on 02.11.16.
 */
public class CityProvider {
    private static final String PREFERENCE_NAME = "weatherApplication";
    private static final String PREFERENCE_CITY_LIST = "cityList";
    private static CityProvider ourInstance;

    public static CityProvider getInstance() {
        if (ourInstance == null) {
            ourInstance = new CityProvider();
        }
        return ourInstance;
    }

    private CityProvider() {
    }

    public void writeCitiesList(@NonNull Context context, @NonNull List<City> cityList) {
        SharedPreferences preferences = context.getApplicationContext().getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        Gson gson = new Gson();
        String jsonString = gson.toJson(cityList);

        editor.putString(PREFERENCE_CITY_LIST, jsonString);
        editor.apply();
    }

    public List<City> getCitiesList(@NonNull Context context) {
        SharedPreferences preferences = context.getApplicationContext().getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);

        String jsonString = preferences.getString(PREFERENCE_CITY_LIST, "");
        List<City> cityList;

        Gson gson = new Gson();
        Type listType = new TypeToken<List<City>>() {
        }.getType();
        cityList = gson.fromJson(jsonString, listType);
        if (cityList == null) {
            cityList = new LinkedList<>();
        }
        return cityList;
    }
}
