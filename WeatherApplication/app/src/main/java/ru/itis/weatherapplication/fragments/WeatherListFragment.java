package ru.itis.weatherapplication.fragments;


import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import java.util.List;

import ru.itis.weatherapplication.interfaces.AsyncTaskListener;
import ru.itis.weatherapplication.weather_api.entities.one_day.City;
import ru.itis.weatherapplication.weather_api.entities.one_day.MyWeather;
import ru.itis.weatherapplication.weather_api.exceptions.NotLoadedException;
import ru.itis.weatherapplication.weather_api.sevice.WeatherService;

/**
 * Created by ilmaz on 03.11.16.
 */

public class WeatherListFragment extends Fragment{
    private AsyncTaskListener listener;
    private WeatherUpdater myTask;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        attachListener(context);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        attachListener(activity);
    }

    private void attachListener(Context context){
        if(context instanceof AsyncTaskListener){
            listener = (AsyncTaskListener) context;
        }
    }

    public void startTask(List<City> cityList){
        if(myTask == null){
            myTask = new WeatherUpdater();
            myTask.execute(cityList);
        }
    }

    public boolean isRunning(){
        return myTask!= null;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    private class WeatherUpdater extends AsyncTask<List<City>, Void, MyWeather> {

        @Override
        protected void onPreExecute() {
            if(listener != null){
                listener.onTaskStarted();
            }
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
            myTask = null;
            if(listener != null){
                listener.onTaskFinished(weather);
            }
        }

        @Override
        protected void onCancelled() {
            myTask = null;
        }
    }
}


