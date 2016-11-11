package ru.itis.weatherapplication.interfaces;

import ru.itis.weatherapplication.weather_api.entities.one_day.MyWeather;

/**
 * Created by ilmaz on 06.11.16.
 */

public interface AsyncTaskListener {
    void onTaskStarted();
    void onTaskFinished(MyWeather weather);
}
