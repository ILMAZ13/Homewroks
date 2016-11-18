package ru.itis.weatherapplication.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import ru.itis.weatherapplication.interfaces.AsyncTaskListener;
import ru.itis.weatherapplication.weather_api.entities.one_day.MyWeather;
import ru.itis.weatherapplication.weather_api.exceptions.NotLoadedException;
import ru.itis.weatherapplication.weather_api.sevice.WeatherService;

/**
 * Created by ilmaz on 07.11.16.
 */

public class CityAddFragment extends Fragment{
    private AsyncTaskListener listener;
    private CityWeatherGetter myTask;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        attachListener(context);
    }

    private void attachListener(Context context) {
        if(context instanceof AsyncTaskListener){
            listener = (AsyncTaskListener) context;
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        attachListener(activity);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public void startTask(String name){
        if(myTask == null) {
            myTask = new CityWeatherGetter();
            myTask.execute(name);
        }
    }

    public boolean isRunning(){
        return myTask != null;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }



    private class CityWeatherGetter extends AsyncTask<String, Void, MyWeather> {


        @Override
        protected void onPreExecute() {
            if(listener != null) {
                listener.onTaskStarted();
            }
        }

        @Override
        protected void onPostExecute(MyWeather weather) {
            myTask = null;
            if(listener != null) {
                listener.onTaskFinished(weather);
            }
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

        @Override
        protected void onCancelled() {
            myTask = null;
        }
    }
}
