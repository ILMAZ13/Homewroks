package ru.itis.galery;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {
    TextView textView;
    RelativeLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Intent intent = getIntent();
        textView = (TextView) findViewById(R.id.text);
        layout = (RelativeLayout) findViewById(R.id.activity_info);
        textView.setText(intent.getStringExtra("text"));
        layout.setBackgroundColor(intent.getIntExtra("color", Color.DKGRAY));
    }
}
