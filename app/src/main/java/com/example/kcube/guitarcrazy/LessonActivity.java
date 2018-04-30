package com.example.kcube.guitarcrazy;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.kcube.guitarcrazy.Adapter.LessonListAdapter;
import com.example.kcube.guitarcrazy.Model.LessonModel;
import com.example.kcube.guitarcrazy.Model.ResultModel;
import com.example.kcube.guitarcrazy.Other.Database_Handler;
import com.example.kcube.guitarcrazy.Other.RecyclerItemClickListener;
import com.example.kcube.guitarcrazy.Other.RetrofitHelper;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LessonActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    LessonListAdapter adapter;
    RetrofitHelper retrofitHelper;
    ArrayList<LessonModel> list;
    Database_Handler database_handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.action_bar_bg));
        recyclerView = (RecyclerView) findViewById(R.id.lesssonRV);
        retrofitHelper = new RetrofitHelper();
        list = new ArrayList<>();
        database_handler = new Database_Handler(getApplicationContext());
        Cursor cursor = database_handler.getdata("select * from lessons");
        if (cursor.getCount() == 0) {
            fectData();
        } else {
            ArrayList<LessonModel> nameList = new ArrayList<>();
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String title = cursor.getString(1);
                String content = cursor.getString(2);
                String image = cursor.getString(3);
//                Log.e("image", cursor.getString(3));
                nameList.add(new LessonModel(title, content, image));
                cursor.moveToNext();
            }
//            Log.e("finish adding", nameList.size() + "");
            SetAdapter(nameList);
        }
    }

    private void SetAdapter(final ArrayList<LessonModel> list) {
        adapter = new LessonListAdapter(getApplicationContext(), list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(LessonActivity.this, LessonDetailActivity.class);
                        intent.putExtra("title", list.get(position).getTitle());
                        intent.putExtra("content", list.get(position).getContent());
                        intent.putExtra("image", list.get(position).getImage());
                        startActivity(intent);
                    }
                })
        );
    }

    private void fectData() {
        Call<ResultModel> call = retrofitHelper.getLesson();
        call.enqueue(new Callback<ResultModel>() {
            @Override
            public void onResponse(Call<ResultModel> call, Response<ResultModel> response) {
                if (response.isSuccessful()) {
                    final ResultModel lessons = response.body();
                    list = lessons.getLesson();
                    SetAdapter(list);
                } else {
                    System.out.println(response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<ResultModel> call, Throwable t) {

            }
        });
    }
}
