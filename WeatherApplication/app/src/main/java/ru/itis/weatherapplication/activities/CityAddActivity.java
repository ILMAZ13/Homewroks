package ru.itis.weatherapplication.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import ru.itis.weatherapplication.R;
import ru.itis.weatherapplication.adapters.CityAddListAdapter;
import ru.itis.weatherapplication.fragments.CityAddFragment;
import ru.itis.weatherapplication.interfaces.AsyncTaskListener;
import ru.itis.weatherapplication.weather_api.entities.one_day.MyWeather;

public class CityAddActivity extends AppCompatActivity implements AsyncTaskListener, View.OnClickListener{
    private Toolbar toolbar;
    private ProgressBar barLoading;
    private ImageButton btnSearch;
    private EditText etSearch;
    private CityAddListAdapter adapter;
    private RecyclerView cityList;
    private MyWeather weather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_add);
        fillToolbar();

        etSearch = (EditText) findViewById(R.id.et_search_text);
        btnSearch = (ImageButton) findViewById(R.id.btn_search);
        btnSearch.setOnClickListener(this);

        adapter = new CityAddListAdapter();
        adapter.setInformation(weather);
        cityList = (RecyclerView) findViewById(R.id.rv_city_list);
        cityList.setLayoutManager(new LinearLayoutManager(this));
        cityList.setAdapter(adapter);

        barLoading = (ProgressBar) findViewById(R.id.pb_loading);

        if(getTaskHolder().isRunning()){
            onTaskStarted();
        }
    }

    private void fillToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Add city");
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
                Toast.makeText(this, weather.getNotLoadedException().getMessage(), Toast.LENGTH_SHORT).show();
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
            Toast.makeText(this, "Write city name!", Toast.LENGTH_SHORT).show();
        } else {
            getTaskHolder().startTask(text);
        }

    }

    private CityAddFragment getTaskHolder(){
        CityAddFragment addFragment = (CityAddFragment) getSupportFragmentManager().findFragmentByTag(CityAddFragment.class.getName());
        if(addFragment == null){
            addFragment = new CityAddFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(addFragment, CityAddFragment.class.getName())
                    .commit();
        }
        return addFragment;
    }
}
