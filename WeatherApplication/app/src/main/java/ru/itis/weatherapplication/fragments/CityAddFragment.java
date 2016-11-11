package ru.itis.weatherapplication.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import ru.itis.weatherapplication.R;
import ru.itis.weatherapplication.adapters.CityAddListAdapter;
import ru.itis.weatherapplication.async_tasks.CityWeatherGetter;
import ru.itis.weatherapplication.interfaces.AsyncTaskListener;
import ru.itis.weatherapplication.weather_api.entities.one_day.MyWeather;

/**
 * Created by ilmaz on 07.11.16.
 */

public class CityAddFragment extends Fragment implements AsyncTaskListener, View.OnClickListener {
    private Toolbar toolbar;
    private ProgressBar barLoading;
    private ImageButton btnSearch;
    private EditText etSearch;
    private CityAddListAdapter adapter;
    private RecyclerView cityList;
    private CityWeatherGetter weatherGetter;
    private MyWeather weather;

    public static CityAddFragment newInstance() {

        Bundle args = new Bundle();

        CityAddFragment fragment = new CityAddFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.city_add_fragment, container, false);

        fillToolbar(view);

        etSearch = (EditText) view.findViewById(R.id.et_search_text);
        btnSearch = (ImageButton) view.findViewById(R.id.btn_search);
        btnSearch.setOnClickListener(this);

        adapter = new CityAddListAdapter();
        adapter.setInformation(weather);
        cityList = (RecyclerView) view.findViewById(R.id.rv_city_list);
        cityList.setLayoutManager(new LinearLayoutManager(getActivity()));
        cityList.setAdapter(adapter);

        barLoading = (ProgressBar) view.findViewById(R.id.pb_loading);

        return view;
    }

    private void fillToolbar(View view) {
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Add city");
    }

    @Override
    public void onTaskStarted() {
        barLoading.setVisibility(View.VISIBLE);
        cityList.setVisibility(View.GONE);
    }

    @Override
    public void onTaskFinished(MyWeather weather) {
        this.weather = weather;
        barLoading.setVisibility(View.GONE);
        if (weather != null) {
            if(weather.isIncludeError()){
                Toast.makeText(getActivity(), weather.getNotLoadedException().getMessage(), Toast.LENGTH_SHORT).show();
            } else {
                cityList.setVisibility(View.VISIBLE);
                adapter.setInformation(weather);
                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onClick(View v) {
        String text = etSearch.getText().toString();
        if (text.length() == 0) {
            Toast.makeText(getActivity(), "Write city name!", Toast.LENGTH_SHORT).show();
        } else {
            weatherGetter = new CityWeatherGetter(this);
            weatherGetter.execute(text);
        }

    }

    @Override
    public void onDetach() {
        if (weatherGetter != null) {
            weatherGetter.cancel(true);
        }
        super.onDetach();
    }
}
