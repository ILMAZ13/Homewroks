package ru.avinews.avinews.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import ru.avinews.avinews.R;
import ru.avinews.avinews.async_tasks.ServerConnectionTask;
import ru.avinews.avinews.entities.Response;
import ru.avinews.avinews.interfaces.TaskListener;

public class CodeVerificationActivity extends AppCompatActivity implements TaskListener {
    private static final String TAG = "code";
    private TextView textView;
    private EditText editText;
    private Button button;
    private ProgressBar progressBar;
    private boolean registered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_verification);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        textView = (TextView) findViewById(R.id.tv_kode_imp);
        editText = (EditText) findViewById(R.id.et_number);
        button = (Button) findViewById(R.id.btn_submit);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        String number = getIntent().getStringExtra("number");
        registered = getIntent().getBooleanExtra("registered", false);
        textView.setText("На ваш номер (" + number + ") был выслан код подтверждения, введите его в это поле");

        if (getConnectionTask(TAG).isRunning()) {
            onTaskStart();
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = editText.getText().toString();
                // TODO: 08.12.16  add code sending task;
                getConnectionTask(TAG).startTask();
            }
        });

    }

    @Override
    public void onTaskStart() {
        progressBar.setVisibility(View.VISIBLE);
        button.setVisibility(View.GONE);
    }

    @Override
    public void onTaskFinish(Response response) {
        progressBar.setVisibility(View.GONE);
        button.setVisibility(View.VISIBLE);
        if (response != null) {
            if (response.success) {
                // TODO: 08.12.16 add number saving
                if (registered) {
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(this, UserConfigActivity.class);
                    startActivity(intent);
                }
                finish();
            } else {
                Toast.makeText(this, response.error_message, Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Connection error", Toast.LENGTH_SHORT).show();
        }
    }

    public ServerConnectionTask getConnectionTask(String tag) {
        ServerConnectionTask task = (ServerConnectionTask) getSupportFragmentManager().findFragmentByTag(ServerConnectionTask.class.getName() + tag);
        if (task == null) {
            task = new ServerConnectionTask();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(task, ServerConnectionTask.class.getName() + tag)
                    .commit();
        }
        return task;
    }

}
