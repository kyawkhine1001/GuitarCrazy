package com.example.kcube.guitarcrazy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {
    TextView aboutTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.action_bar_bg));
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        aboutTV = (TextView) findViewById(R.id.aboutTV);
        aboutTV.setText(R.string.aboutus);

    }
}
