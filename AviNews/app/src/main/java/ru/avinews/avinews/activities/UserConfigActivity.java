package ru.avinews.avinews.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import ru.avinews.avinews.R;

public class UserConfigActivity extends AppCompatActivity {
    private Button button;
    // TODO: 09.12.16 add fields

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_config);

        button = (Button) findViewById(R.id.btn_save);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 09.12.16 saving to shared preferences
                Intent intent = new Intent(UserConfigActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
