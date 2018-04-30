package com.example.kcube.guitarcrazy;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.kcube.guitarcrazy.Adapter.LyricAdapter;
import com.example.kcube.guitarcrazy.Model.LyricModel;
import com.example.kcube.guitarcrazy.Model.SingerModel;
import com.example.kcube.guitarcrazy.Other.Database_Handler;
import com.example.kcube.guitarcrazy.Other.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FavouriteActivity extends AppCompatActivity {
    Database_Handler database_handler;
    RecyclerView recyclerView;
    LyricAdapter lyricAdapter;
    ArrayList<LyricModel> lyricList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.action_bar_bg));
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        SetAdapter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        lyricAdapter.SwapList(getList());
    }

    private void SetAdapter() {
        recyclerView = (RecyclerView) findViewById(R.id.songLyricsView);
        lyricList = getList();
        lyricAdapter = new LyricAdapter(this, lyricList);
        recyclerView.setAdapter(lyricAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getApplicationContext(), LyricShowActivity.class);
                        intent.putExtra("id", lyricList.get(position).getId());
                        intent.putExtra("title", lyricList.get(position).getTitle());
                        intent.putExtra("image", lyricList.get(position).getImage());
                        intent.putExtra("fav", lyricList.get(position).getFav());
                        startActivity(intent);
                    }
                })
        );
    }

    public ArrayList<LyricModel> getList() {
        database_handler = new Database_Handler(this);
        ArrayList<LyricModel> lyriclist = new ArrayList<>();
        Cursor favlyriccursor = database_handler.getdata("select * from lyrics where fav='1'");
        if (favlyriccursor.getCount() == 0) {
//            Toast.makeText(this, "No Favourite Lyrics", Toast.LENGTH_SHORT).show();
        } else {

            favlyriccursor.moveToFirst();
            while (!favlyriccursor.isAfterLast()) {
                int id = favlyriccursor.getInt(0);
                String title = favlyriccursor.getString(1);
                String image = favlyriccursor.getString(2);
                int singer_id = favlyriccursor.getInt(3);
                String fav = favlyriccursor.getString(4);
                lyriclist.add(new LyricModel(id, title, image, singer_id, fav));
                favlyriccursor.moveToNext();
            }
        }
        Collections.sort(lyriclist, new Comparator<LyricModel>() {
            @Override
            public int compare(LyricModel o1, LyricModel o2) {
                return o1.getTitle().compareToIgnoreCase(o2.getTitle());
            }
        });
        return lyriclist;
    }

}
