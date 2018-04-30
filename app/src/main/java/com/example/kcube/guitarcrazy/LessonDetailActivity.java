package com.example.kcube.guitarcrazy;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jsibbold.zoomage.ZoomageView;

import de.ailis.pherialize.MixedArray;
import de.ailis.pherialize.Pherialize;

import static com.example.kcube.guitarcrazy.Other.RetrofitHelper.PHOTO_BASE_URL;

public class LessonDetailActivity extends AppCompatActivity {
    TextView lessonContent;
    ZoomageView lessonImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_detail);
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.action_bar_bg));
        getSupportActionBar().setTitle(getIntent().getStringExtra("title"));
        String content = getIntent().getStringExtra("content");

        lessonContent = (TextView) findViewById(R.id.content);
        lessonImage = (ZoomageView) findViewById(R.id.lessonimage);

        lessonContent.setText(content);

        String image = getIntent().getStringExtra("image");
        MixedArray imagearray = Pherialize.unserialize(image).toArray();
        Glide.with(this).load(PHOTO_BASE_URL + imagearray.getString(0)).into(lessonImage);

//        ImageView i = new ImageView(LessonDetailActivity.this);
    }
}
