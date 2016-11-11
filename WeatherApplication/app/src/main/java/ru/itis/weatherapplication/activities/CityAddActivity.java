package ru.itis.weatherapplication.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ru.itis.weatherapplication.R;
import ru.itis.weatherapplication.fragments.CityAddFragment;

public class CityAddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_add);
        CityAddFragment addFragment = (CityAddFragment) getSupportFragmentManager().findFragmentByTag(CityAddFragment.class.getName());
        if(addFragment == null){
            addFragment = CityAddFragment.newInstance();
        }
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_city_add, addFragment, CityAddFragment.class.getName())
                .commit();
    }
}
