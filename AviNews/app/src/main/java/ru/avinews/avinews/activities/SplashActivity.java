package ru.avinews.avinews.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import ru.avinews.avinews.R;
import ru.avinews.avinews.async_tasks.ServerConnectionTask;
import ru.avinews.avinews.entities.Response;
import ru.avinews.avinews.interfaces.TaskListener;

public class SplashActivity extends AppCompatActivity implements TaskListener{
    private static final String TAG = "splash";
    private EditText editText;
    private Button submit;
    private ProgressBar progressBar;
    private TextInputLayout inputLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // TODO: 08.12.16 add condition to already logged in from preferences and intent to main page

        inputLayout = (TextInputLayout) findViewById(R.id.text_inp_l);
        editText = (EditText) findViewById(R.id.et_number);
        submit = (Button) findViewById(R.id.btn_success);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = editText.getText().toString();
                // TODO: 08.12.16 add number sending task
                getConnectionTask(TAG).startTask();
            }
        });

        if(getConnectionTask(TAG).isRunning()){
            onTaskStart();
        }
    }

    @Override
    public void onTaskStart() {
        progressBar.setVisibility(View.VISIBLE);
        submit.setVisibility(View.INVISIBLE);
        inputLayout.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onTaskFinish(Response response) {
        progressBar.setVisibility(View.GONE);
        submit.setVisibility(View.VISIBLE);
        inputLayout.setVisibility(View.VISIBLE);
        if(response != null){
            if(response.success){
                Intent intent = new Intent(this, CodeVerificationActivity.class);
                intent.putExtra("number", editText.getText().toString());
                intent.putExtra("registered", true);
                startActivity(intent);
                finish();
            } else {
                Intent intent = new Intent(this, RulesActivity.class);
                intent.putExtra("number", editText.getText().toString());
                startActivity(intent);
                finish();
            }
        } else {
            Toast.makeText(this, "Connection error", Toast.LENGTH_SHORT).show();
        }
    }

    public ServerConnectionTask getConnectionTask(String tag){
        ServerConnectionTask task = (ServerConnectionTask) getSupportFragmentManager().findFragmentByTag(ServerConnectionTask.class.getName() + tag);
        if(task == null){
            task = new ServerConnectionTask();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(task, ServerConnectionTask.class.getName() + tag)
                    .commit();
        }
        return task;
    }
}

