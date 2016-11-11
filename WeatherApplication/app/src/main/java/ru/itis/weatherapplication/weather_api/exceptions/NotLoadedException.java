package ru.itis.weatherapplication.weather_api.exceptions;

/**
 * Created by ilmaz on 04.11.16.
 */

public class NotLoadedException extends Exception {
    public NotLoadedException() {
        super();
    }

    public NotLoadedException(String message) {
        super(message);
    }
}
