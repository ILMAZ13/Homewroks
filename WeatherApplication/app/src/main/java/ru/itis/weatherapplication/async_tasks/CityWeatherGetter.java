package ru.itis.weatherapplication.async_tasks;

import android.os.AsyncTask;

import ru.itis.weatherapplication.interfaces.AsyncTaskListener;
import ru.itis.weatherapplication.weather_api.entities.one_day.MyWeather;
import ru.itis.weatherapplication.weather_api.exceptions.NotLoadedException;
import ru.itis.weatherapplication.weather_api.sevice.WeatherService;

/**
 * Created by ilmaz on 07.11.16.
 */

public class CityWeatherGetter extends AsyncTask<String, Void, MyWeather> {
    private AsyncTaskListener listener;

    public CityWeatherGetter(AsyncTaskListener listener) {
        this.listener = listener;
    }

    @Override
    protected void onPreExecute() {
        listener.onTaskStarted();
    }

    @Override
    protected void onPostExecute(MyWeather weather) {
        listener.onTaskFinished(weather);
    }

    @Override
    protected MyWeather doInBackground(String... params) {
        MyWeather weather = null;
        try {
            weather = WeatherService.getCityWeatherByName(params[0]);
        } catch (Exception e) {
            e.printStackTrace();
            weather = new MyWeather(new NotLoadedException("Connection error"));
        }
        return weather;
    }
}
