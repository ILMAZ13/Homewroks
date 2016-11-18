package ru.itis.weatherapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import ru.itis.weatherapplication.R;
import ru.itis.weatherapplication.adapters.WeatherListAdapter;
import ru.itis.weatherapplication.fragments.WeatherListFragment;
import ru.itis.weatherapplication.interfaces.AsyncTaskListener;
import ru.itis.weatherapplication.weather_api.entities.one_day.City;
import ru.itis.weatherapplication.weather_api.entities.one_day.MyWeather;
import ru.itis.weatherapplication.weather_api.exceptions.NotLoadedException;
import ru.itis.weatherapplication.weather_api.providers.CityProvider;
import ru.itis.weatherapplication.weather_api.providers.WeatherProvider;

public class WeatherListActivity extends AppCompatActivity implements AsyncTaskListener{
    private WeatherListAdapter mAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private FloatingActionButton mButtonAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fillToolbar();

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.sr_refresh);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                updateInformation();
            }
        });
        if(getTaskHolder().isRunning()){
            mSwipeRefreshLayout.setRefreshing(true);
        } else {
            mSwipeRefreshLayout.setRefreshing(false);
        }

        mAdapter = new WeatherListAdapter(this, getSupportFragmentManager());
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);

        mButtonAdd = (FloatingActionButton) findViewById(R.id.btn_add);
        mButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddClicked();
            }
        });

        if(savedInstanceState == null) {
            updateInformation();
        }

    }



    private void onAddClicked() {
        Intent intent = new Intent(this, CityAddActivity.class);
        startActivity(intent);
    }


    private void fillToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_refresh :
                        updateInformation();
                        break;
                    case R.id.menu_add :
                        onAddClicked();
                        break;
                }
                return true;
            }
        });
    }

    public void updateInformation(){
        List<City> cityList = CityProvider.getInstance().getCitiesList(this);
        getTaskHolder().startTask(cityList);
    }

    public void notifyDataSetChanged(){
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onTaskStarted() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void onTaskFinished(MyWeather weather) {
        mSwipeRefreshLayout.setRefreshing(false);
        try {
            WeatherProvider.getInstance().saveOneDayWeather(this, weather);
            notifyDataSetChanged();
        } catch (NotLoadedException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.weather_list_menu, menu);
        return true;
    }

    private WeatherListFragment getTaskHolder(){
        WeatherListFragment listFragment = (WeatherListFragment) getSupportFragmentManager().findFragmentByTag(WeatherListFragment.class.getName());
        if(listFragment == null){
            listFragment = new WeatherListFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(listFragment, WeatherListFragment.class.getName())
                    .commit();
        }
        return listFragment;
    }
}
