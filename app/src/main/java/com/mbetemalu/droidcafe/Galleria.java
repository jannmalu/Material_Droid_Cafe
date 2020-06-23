package com.mbetemalu.droidcafe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class Galleria extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galleria);

        TextView galleriaTitle = findViewById(R.id.galleria_title);
        galleriaTitle.setText(getIntent().getStringExtra("gTitle"));


        ImageView galleriaImage = findViewById(R.id.galleria_image);
        Glide.with(this).load(getIntent().getIntExtra("gImage",0)).into(galleriaImage);
    }
}
