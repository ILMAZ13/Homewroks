package ru.itis.weatherapplication.async_tasks;

import android.os.AsyncTask;

import java.util.List;

import ru.itis.weatherapplication.interfaces.AsyncTaskListener;
import ru.itis.weatherapplication.weather_api.entities.one_day.City;
import ru.itis.weatherapplication.weather_api.entities.one_day.MyWeather;
import ru.itis.weatherapplication.weather_api.exceptions.NotLoadedException;
import ru.itis.weatherapplication.weather_api.sevice.WeatherService;

/**
 * Created by ilmaz on 06.11.16.
 */

public class WeatherUpdater extends AsyncTask<List<City>, Void, MyWeather> {
    private final AsyncTaskListener listener;

    public WeatherUpdater(AsyncTaskListener listener) {
        this.listener = listener;
    }

    @Override
    protected void onPreExecute() {
        listener.onTaskStarted();
    }

    @Override
    protected MyWeather doInBackground(List<City>... params) {
        MyWeather weather;
        try {
            weather = WeatherService.getAllWeatherDataAboutCities(params[0]);
            if(weather == null){
                weather = new MyWeather(new NotLoadedException("Can`t load from server"));
            }
//            TimeUnit.SECONDS.sleep(5);
        } catch (Exception e) {
            weather = new MyWeather(new NotLoadedException("Can`t load from server"));
        }
        return weather;
    }

    @Override
    protected void onPostExecute(MyWeather weather) {
        listener.onTaskFinished(weather);
    }
}