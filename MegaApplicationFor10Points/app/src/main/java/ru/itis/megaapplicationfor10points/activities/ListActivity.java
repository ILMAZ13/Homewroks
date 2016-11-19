package ru.itis.megaapplicationfor10points.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import ru.itis.megaapplicationfor10points.R;
import ru.itis.megaapplicationfor10points.fragments.ListFragment;

public class ListActivity extends AppCompatActivity {
    private ListFragment listFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listFragment = ListFragment.newInstance();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.list, listFragment, ListFragment.class.getName())
                .commit();
    }

    public void notifyDataSetChanged(){
        listFragment.notifyDataSetChanged();
    }
}
