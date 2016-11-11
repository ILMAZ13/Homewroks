package ru.itis.weatherapplication.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.Toast;

import java.util.List;

import ru.itis.weatherapplication.activities.WeatherListActivity;
import ru.itis.weatherapplication.weather_api.entities.one_day.City;
import ru.itis.weatherapplication.weather_api.providers.CityProvider;

/**
 * Created by ilmaz on 11.11.16.
 */

public class DialogDeletionFragment extends DialogFragment implements DialogInterface.OnClickListener {
    private City city;
    public static DialogDeletionFragment newInstance(City city) {

        Bundle args = new Bundle();
        args.putSerializable("city", city);
        DialogDeletionFragment fragment = new DialogDeletionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        city = (City) getArguments().getSerializable("city");
        AlertDialog.Builder adb = new AlertDialog.Builder(getActivity())
                .setTitle("Удалить!")
                .setPositiveButton("Да", this)
                .setNegativeButton("Нет", this)
                .setMessage("Удалить этот город?");
        return adb.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if (which == Dialog.BUTTON_POSITIVE) {
            List<City> cities = CityProvider.getInstance().getCitiesList(getActivity());
            cities.remove(city);
            CityProvider.getInstance().writeCitiesList(getActivity(), cities);
            Toast.makeText(getActivity(), "City deleted", Toast.LENGTH_SHORT).show();
            if(getActivity() instanceof WeatherListActivity){
                ((WeatherListActivity) getActivity()).update();
            }
        }
    }
}
