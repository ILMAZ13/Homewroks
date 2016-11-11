package ru.itis.weatherapplication.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.itis.weatherapplication.R;
import ru.itis.weatherapplication.view_holders.CityAddListViewHolder;
import ru.itis.weatherapplication.weather_api.entities.one_day.MyWeather;
import ru.itis.weatherapplication.weather_api.exceptions.NotLoadedException;

/**
 * Created by ilmaz on 08.11.16.
 */

public class CityAddListAdapter extends RecyclerView.Adapter<CityAddListViewHolder> {
    private MyWeather weather;

    public void setInformation(MyWeather weather){
        this.weather = weather;
    }

    @Override
    public CityAddListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.city_add_list_item, parent, false);
        return new CityAddListViewHolder(view, parent.getContext());
    }

    @Override
    public void onBindViewHolder(CityAddListViewHolder holder, int position) {
        try {
            holder.bind(weather.getList().get(position).getCity());
        } catch (NotLoadedException e) {
            e.printStackTrace();
        } catch (NullPointerException e){}
    }

    @Override
    public int getItemCount() {
        try {
            return weather.getList().size();
        } catch (NotLoadedException e) {
            return 0;
        } catch (NullPointerException e){
            return 0;
        }
    }

}
