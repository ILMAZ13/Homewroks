package ru.itis.megaapplicationfor10points.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import ru.itis.megaapplicationfor10points.R;
import ru.itis.megaapplicationfor10points.interfaces.TaskGettable;
import ru.itis.megaapplicationfor10points.interfaces.TaskListener;
import ru.itis.megaapplicationfor10points.interfaces.TaskPattern;
import ru.itis.megaapplicationfor10points.tasks.SomeStupidTask;

public class SplashActivity extends AppCompatActivity implements TaskListener, TaskGettable{
    private final String TAG = "splash";


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getTask().startTask();
    }

    @Override
    public void onTaskStart() {

    }

    @Override
    public void onTaskFinished() {
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onTaskCanceled() {

    }

    @Override
    public void onTaskUpdate(int i) {

    }

    @Override
    public TaskPattern getTask() {
        SomeStupidTask someStupidTask = (SomeStupidTask) getSupportFragmentManager().findFragmentByTag(SomeStupidTask.class.getName() + TAG);
        if(someStupidTask == null){
            someStupidTask = new SomeStupidTask();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(someStupidTask, SomeStupidTask.class.getName() + TAG)
                    .commit();
        }
        return someStupidTask;
    }
}
