package ru.itis.weatherapplication.weather_api.sevice;

import java.util.List;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.itis.weatherapplication.weather_api.entities.one_day.City;
import ru.itis.weatherapplication.weather_api.entities.one_day.MyWeather;

/**
 * Created by ilmaz on 02.11.16.
 */

public class WeatherService {
    private static WeatherAPI weatherAPI;
    private static final String KEY = "874d54c18aa696a5b0a7921b50e35a73";


    public static WeatherAPI getAPI(){
        if(weatherAPI == null){
            Retrofit retrofit  = new Retrofit.Builder()
                    .baseUrl("http://api.openweathermap.org/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            weatherAPI = retrofit.create(WeatherAPI.class);
        }
        return weatherAPI;
    }

    public static MyWeather getAllWeatherDataAboutCities(List<City> cities) throws Exception {
        String idString = "";
        for (City city : cities) {
            idString = idString + "," + city.getId();
        }
        if(idString.length()>1) {
            idString = idString.substring(1);
        }
        Response<MyWeather> response = WeatherService.getAPI().getWeatherById(idString, KEY).execute();
        return response.body();
    }

    public static MyWeather getCityWeatherByName(String name) throws Exception{
        Response<MyWeather> response = WeatherService.getAPI().getWeatherByName(name, KEY).execute();
        return response.body();
    }
}
