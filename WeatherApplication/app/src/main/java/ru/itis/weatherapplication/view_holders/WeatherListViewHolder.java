package ru.itis.weatherapplication.view_holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import ru.itis.weatherapplication.R;
import ru.itis.weatherapplication.weather_api.entities.one_day.List;

/**
 * Created by ilmaz on 03.11.16.
 */

public class WeatherListViewHolder extends RecyclerView.ViewHolder {
    private TextView tvCityName;
    private TextView tvCountry;
    private TextView tvTemperature;


    public WeatherListViewHolder(View itemView) {
        super(itemView);
        tvCityName = (TextView) itemView.findViewById(R.id.tv_city_name);
        tvCountry = (TextView) itemView.findViewById(R.id.tv_country);
        tvTemperature = (TextView) itemView.findViewById(R.id.tv_temperature);
    }

    public void bind(List weatherList) {
        tvCityName.setText(weatherList.getName());
        tvCountry.setText(weatherList.getSys().getCountry());
        double temp = weatherList.getMain().getTemp();
        String sTemp = String.valueOf(Math.round(temp));
        if (temp > 0) {
            sTemp = "+" + sTemp;
        }
        tvTemperature.setText(sTemp);
    }
}
