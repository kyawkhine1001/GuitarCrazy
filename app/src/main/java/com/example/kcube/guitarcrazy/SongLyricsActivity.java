package com.example.kcube.guitarcrazy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.kcube.guitarcrazy.Adapter.LyricAdapter;
import com.example.kcube.guitarcrazy.Adapter.SingerAdapter;
import com.example.kcube.guitarcrazy.Model.LyricModel;
import com.example.kcube.guitarcrazy.Model.SingerModel;
import com.example.kcube.guitarcrazy.Other.RecyclerItemClickListener;
import com.example.kcube.guitarcrazy.Other.RetrofitHelper;

import java.util.ArrayList;
import java.util.List;

public class SongLyricsActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    LyricAdapter lyricAdapter;
    List<LyricModel> list;
    String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_lyrics);
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.action_bar_bg));
        recyclerView = (RecyclerView) findViewById(R.id.songLyricsView);
        fetchData();
        title = GuitarCrazy.sentSingerModel.getName();
        getSupportActionBar().setTitle(title);

    }

    private void fetchData() {
        list = GuitarCrazy.lyricModelArrayList;
        lyricAdapter = new LyricAdapter(getApplicationContext(), list);
        recyclerView.setAdapter(lyricAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getApplicationContext(), LyricShowActivity.class);
                        GuitarCrazy.sentLyricModel = list.get(position);
                        startActivity(intent);
                    }
                })
        );
    }
}
