package ru.itis.megaapplicationfor10points.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import ru.itis.megaapplicationfor10points.R;
import ru.itis.megaapplicationfor10points.entities.Notification;
import ru.itis.megaapplicationfor10points.interfaces.TaskGettable;
import ru.itis.megaapplicationfor10points.interfaces.TaskListener;
import ru.itis.megaapplicationfor10points.interfaces.TaskPattern;
import ru.itis.megaapplicationfor10points.tasks.SomeStupidTask;

public class EditActivity extends AppCompatActivity implements TaskListener, TaskGettable{
    public static final int EDITED_KEY = 101;
    public static final int NOT_EDITED_KEY = 102;
    private static final String TAG = "edit";
    private Notification notification;
    private EditText etTitle;
    private EditText etText;
    private Button btnEdit;
    private Button btnCancel;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etText = (EditText) findViewById(R.id.et_text);
        etTitle = (EditText) findViewById(R.id.et_title);
        btnEdit = (Button) findViewById(R.id.btn_edit);
        btnCancel = (Button) findViewById(R.id.btn_cancel);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);

        if(getTask().isRunning()){
            progressBar.setVisibility(View.VISIBLE);
        }

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getTask().startTask();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(NOT_EDITED_KEY);
                finish();
            }
        });

        Intent intent = getIntent();
        notification = (Notification) intent.getSerializableExtra("notification");
        if(notification != null){
            etText.setText(notification.text);
            etTitle.setText(notification.title);
        }
    }

    @Override
    public void onTaskStart() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onTaskFinished() {
        progressBar.setVisibility(View.GONE);
        if(etText.getText().toString().equals(notification.text) && etTitle.getText().toString().equals(notification.title)){
            setResult(NOT_EDITED_KEY);
        } else {
            Intent intent = new Intent();
            Notification notification = new Notification();
            notification.text = etText.getText().toString();
            notification.title = etTitle.getText().toString();
            intent.putExtra("notification", notification);
            setResult(EDITED_KEY, intent);
        }
        finish();
    }

    @Override
    public void onTaskCanceled() {

    }

    @Override
    public void onTaskUpdate(int i) {
        progressBar.setProgress(i);
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
