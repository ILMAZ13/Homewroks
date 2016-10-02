package ru.itis.photogallery;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        ImageView imageView = (ImageView) findViewById(R.id.iv_image);
//        imageView.setImageBitmap((Bitmap) getIntent().getParcelableExtra("image"));
        Glide.with(this).load(this.getIntent().getStringExtra("image")).fitCenter().into(imageView);
    }
}
