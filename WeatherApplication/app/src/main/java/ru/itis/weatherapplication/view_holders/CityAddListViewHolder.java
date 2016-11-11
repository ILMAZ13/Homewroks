package ru.itis.weatherapplication.view_holders;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import ru.itis.weatherapplication.R;
import ru.itis.weatherapplication.activities.WeatherListActivity;
import ru.itis.weatherapplication.weather_api.entities.one_day.City;
import ru.itis.weatherapplication.weather_api.providers.CityProvider;

/**
 * Created by ilmaz on 08.11.16.
 */

public class CityAddListViewHolder extends RecyclerView.ViewHolder{
    private TextView tvCityText;
    private Button btnAdd;
    private Context context;
    public CityAddListViewHolder(View itemView, Context context) {
        super(itemView);
        this.context = context;
        tvCityText = (TextView) itemView.findViewById(R.id.tv_city_name);
        btnAdd = (Button) itemView.findViewById(R.id.btn_add);
    }

    public void bind(final City city){
        tvCityText.setText(city.getName() + ", " + city.getCountry());
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<City> cityList = CityProvider.getInstance().getCitiesList(context);
                if(cityList.contains(city)){
                    Toast.makeText(context, "City already exist", Toast.LENGTH_LONG).show();
                } else {
                    if(city.getId() == 0){
                        Toast.makeText(context, "Can't add this city, cause server problem", Toast.LENGTH_LONG).show();
                    } else {
                        cityList.add(city);
                        CityProvider.getInstance().writeCitiesList(context, cityList);
                        Toast.makeText(context, "City added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context, WeatherListActivity.class);
                        context.startActivity(intent);
                    }
                }
            }
        });
    }
}
