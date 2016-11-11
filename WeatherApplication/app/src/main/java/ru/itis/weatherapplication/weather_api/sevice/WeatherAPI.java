package ru.itis.weatherapplication.weather_api.sevice;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.itis.weatherapplication.weather_api.entities.one_day.MyWeather;

/**
 * Created by ilmaz on 02.11.16.
 */

public interface WeatherAPI {
    @GET("/data/2.5/group?units=metric&lang=ru")
    Call<MyWeather> getWeatherById(@Query("id") String id, @Query("appid") String apiKey);

    @GET("/data/2.5/find?units=metric&lang=ru")
    Call<MyWeather> getWeatherByName(@Query("q") String name, @Query("appid") String apiKey);

}
