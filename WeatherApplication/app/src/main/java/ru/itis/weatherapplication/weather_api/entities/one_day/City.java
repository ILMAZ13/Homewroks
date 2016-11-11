package ru.itis.weatherapplication.weather_api.entities.one_day;

import java.io.Serializable;

/**
 * Created by ilmaz on 02.11.16.
 */

public class City implements Serializable{
    private int id;
    private String name;
    private String country;

    public City(int id, String name, String country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof City) {
            return ((City) obj).id == id;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
