package ru.itis.megaapplicationfor10points.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.util.List;

import ru.itis.megaapplicationfor10points.R;
import ru.itis.megaapplicationfor10points.entities.Notification;
import ru.itis.megaapplicationfor10points.fragments.InformationFragment;
import ru.itis.megaapplicationfor10points.fragments.ListFragment;
import ru.itis.megaapplicationfor10points.providers.NotificationProvider;

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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == EditActivity.EDITED_KEY) {
            Notification notification = (Notification) data.getSerializableExtra("notification");
            if (notification != null) {
                List<Notification> notifications = NotificationProvider.getInstance().getNotificationList(this);
                notifications.get(requestCode).text = notification.text;
                notifications.get(requestCode).title = notification.title;
                NotificationProvider.getInstance().writeNotificationList(this, notifications);
                listFragment.notifyDataSetChanged();
                Toast.makeText(this, "Notification changed", Toast.LENGTH_SHORT).show();

                InformationFragment fragment = (InformationFragment) getSupportFragmentManager().findFragmentByTag(InformationFragment.class.getName());
                if (fragment != null) {
                    fragment.fillInformation(notification);
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
