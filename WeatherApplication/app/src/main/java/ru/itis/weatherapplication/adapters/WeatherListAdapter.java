package ru.itis.weatherapplication.adapters;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import ru.itis.weatherapplication.R;
import ru.itis.weatherapplication.fragments.DialogDeletionFragment;
import ru.itis.weatherapplication.view_holders.WeatherListViewHolder;
import ru.itis.weatherapplication.weather_api.entities.one_day.City;
import ru.itis.weatherapplication.weather_api.entities.one_day.MyWeather;
import ru.itis.weatherapplication.weather_api.exceptions.NotLoadedException;
import ru.itis.weatherapplication.weather_api.providers.WeatherProvider;

/**
 * Created by ilmaz on 03.11.16.
 */

public class WeatherListAdapter extends RecyclerView.Adapter<WeatherListViewHolder> {
    private Context context;
    private MyWeather weather;
    private FragmentManager fr;

    public WeatherListAdapter(Context context, FragmentManager fr) {
        this.fr = fr;
        this.context = context;
    }

    @Override
    public WeatherListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_item, parent, false);
        return new WeatherListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WeatherListViewHolder holder, final int position) {
        final City city;
        try {
            city = weather.getList().get(position).getCity();
            holder.bind(weather.getList().get(position));
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {

                @Override
                public boolean onLongClick(View v) {
                    if (city != null) {
                        DialogDeletionFragment dialogDeletionFragment = DialogDeletionFragment.newInstance(city);
                        dialogDeletionFragment.show(fr, "tag");
                    }
                    return true;
                }
            });
        } catch (NotLoadedException e) {
            Toast.makeText(context, "Can`t load weather information", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getItemCount() {
        weather = WeatherProvider.getInstance().getOneDayWeather(context);
        try {
            return weather.getList().size();
        } catch (NotLoadedException e) {
            return 0;
        }
    }
}
