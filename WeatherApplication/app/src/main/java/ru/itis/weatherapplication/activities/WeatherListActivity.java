package ru.itis.weatherapplication.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;

import ru.itis.weatherapplication.R;
import ru.itis.weatherapplication.fragments.WeatherListFragment;

public class WeatherListActivity extends AppCompatActivity {
    private WeatherListFragment listFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listFragment = (WeatherListFragment) getSupportFragmentManager().findFragmentByTag(WeatherListFragment.class.getName());
        if(listFragment == null){
            listFragment = WeatherListFragment.newInstance();
        }
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_activity, listFragment, WeatherListFragment.class.getName())
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.weather_list_menu, menu);
        return true;
    }

    public void update(){
        if(listFragment != null){
            listFragment.updateInformation();
        }
    }
}
