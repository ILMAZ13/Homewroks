package ru.itis.weatherapplication.weather_api.entities.one_day;

import java.io.Serializable;

/**
 * Created by ilmaz on 10.11.16.
 */

public class ShortWeather implements Serializable {
    private String cityName;
    private String weatherDescription;
    private double temp;

    public ShortWeather(String cityName, String weatherDescription, double temp) {
        this.cityName = cityName;
        this.weatherDescription = weatherDescription;
        this.temp = temp;
    }

    public String getCityName() {
        return cityName;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public double getTemp() {
        return temp;
    }
}
