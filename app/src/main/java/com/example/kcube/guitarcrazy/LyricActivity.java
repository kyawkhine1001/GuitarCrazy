package com.example.kcube.guitarcrazy;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.kcube.guitarcrazy.Adapter.ChordAdapter;
import com.example.kcube.guitarcrazy.Adapter.SingerAdapter;
import com.example.kcube.guitarcrazy.Model.LessonModel;
import com.example.kcube.guitarcrazy.Model.LyricModel;
import com.example.kcube.guitarcrazy.Model.NoteModel;
import com.example.kcube.guitarcrazy.Model.ResultModel;
import com.example.kcube.guitarcrazy.Model.SingerModel;
import com.example.kcube.guitarcrazy.Other.Database_Handler;
import com.example.kcube.guitarcrazy.Other.RecyclerItemClickListener;
import com.example.kcube.guitarcrazy.Other.RetrofitHelper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LyricActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    SingerAdapter singerAdapter;
    ArrayList<SingerModel> list;
    RetrofitHelper retrofitHelper;
    Database_Handler database_handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lyric);
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.action_bar_bg));
        recyclerView = (RecyclerView) findViewById(R.id.lyricFolderList);
        retrofitHelper = new RetrofitHelper();
        list = new ArrayList<>();
        database_handler = new Database_Handler(this);
        Cursor singercursor = database_handler.getdata("select * from singers");
        Cursor lyriccursor = database_handler.getdata("select * from lyrics");
        if (singercursor.getCount() == 0 && lyriccursor.getCount() == 0) {
            fetchData();
        } else {
            ArrayList<SingerModel> nameList = new ArrayList<>();
            ArrayList<LyricModel> lyricList = new ArrayList<>();
            singercursor.moveToFirst();
            while (!singercursor.isAfterLast()) {
                int id = singercursor.getInt(0);
                String name = singercursor.getString(1);
                nameList.add(new SingerModel(id, name));
                singercursor.moveToNext();
            }
//            Log.e("finish adding", nameList.size() + "");

            lyriccursor.moveToFirst();
            while (!lyriccursor.isAfterLast()) {
                int id = lyriccursor.getInt(0);
                String title = lyriccursor.getString(1);
                String image = lyriccursor.getString(2);
                int singer_id = lyriccursor.getInt(3);
                String fav = lyriccursor.getString(4);
//                Log.e("singer_id", singer_id + "");
                lyricList.add(new LyricModel(id, title, image, singer_id, fav));
                lyriccursor.moveToNext();
            }
//            Log.e("finish lyric", lyricList.size() + "");
            SetAdapter(nameList, lyricList);
        }
    }

    private void SetAdapter(final ArrayList<SingerModel> list, final ArrayList<LyricModel> lyriclist) {
        singerAdapter = new SingerAdapter(getApplicationContext(), list);
        recyclerView.setAdapter(singerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent songintent = new Intent(getApplicationContext(), SongLyricsActivity.class);
                        ArrayList<LyricModel> arraylist = new ArrayList<LyricModel>();
                        for (int i = 0; i < lyriclist.size(); i++) {
                            if (list.get(position).getId() == lyriclist.get(i).getSinger_id()) {
                                arraylist.add(lyriclist.get(i));
                            }
                        }
                        GuitarCrazy.lyricModelArrayList = arraylist;
                        GuitarCrazy.sentSingerModel = list.get(position);
                        startActivity(songintent);
                    }
                })
        );
    }

    private void fetchData() {
        Call<ResultModel> call = retrofitHelper.getSinger();
        call.enqueue(new Callback<ResultModel>() {
            @Override
            public void onResponse(Call<ResultModel> call, Response<ResultModel> response) {
                if (response.isSuccessful()) {
                    final ResultModel result = response.body();
                    list = result.getSinger();
                    SetAdapter(list, result.getLyric());
                }
            }

            @Override
            public void onFailure(Call<ResultModel> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.singer_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search:
                Intent searchintent = new Intent(getApplicationContext(), SearchSingerActivity.class);
                startActivity(searchintent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
