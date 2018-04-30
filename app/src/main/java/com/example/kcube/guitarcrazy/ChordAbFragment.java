package com.example.kcube.guitarcrazy;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kcube.guitarcrazy.Adapter.ChordAdapter;
import com.example.kcube.guitarcrazy.Model.ChordModel;
import com.example.kcube.guitarcrazy.Model.NoteModel;
import com.example.kcube.guitarcrazy.Model.ResultModel;
import com.example.kcube.guitarcrazy.Other.Database_Handler;
import com.example.kcube.guitarcrazy.Other.RecyclerItemClickListener;
import com.example.kcube.guitarcrazy.Other.RetrofitHelper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChordAbFragment extends Fragment {

    RecyclerView recyclerView;
    ChordAdapter chordAdapter;
    ArrayList<ChordModel> list;
    RetrofitHelper retrofitHelper;
    Database_Handler database_handler;

    public ChordAbFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chord_c, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        retrofitHelper = new RetrofitHelper();
        list = new ArrayList<>();
        database_handler = new Database_Handler(getContext());
        Cursor chordcursor = database_handler.getdata("select * from chords where type='9'");
        Cursor notecursor = database_handler.getdata("select * from notes");
        if (chordcursor.getCount() == 0) {
            fetchData();
        } else {
            ArrayList<ChordModel> chordList = new ArrayList<>();
            ArrayList<NoteModel> noteList = new ArrayList<>();
            chordcursor.moveToFirst();
            while (!chordcursor.isAfterLast()) {
                int id = chordcursor.getInt(0);
                String name = chordcursor.getString(1);
                chordList.add(new ChordModel(id, name));
                chordcursor.moveToNext();
            }
            Log.e("finish adding", chordList.size() + "");

            notecursor.moveToFirst();
            while (!notecursor.isAfterLast()) {
                int fret = notecursor.getInt(1);
                int string = notecursor.getInt(2);
                int finger = notecursor.getInt(3);
                int chord_id = notecursor.getInt(4);
                noteList.add(new NoteModel(fret, string, finger, chord_id));
                notecursor.moveToNext();
            }
            Log.e("finish note", noteList.size() + "");
            SetAdapter(chordList, noteList);
        }
        return view;
    }

    private void SetAdapter(final ArrayList<ChordModel> chordList, final ArrayList<NoteModel> noteList) {
        chordAdapter = new ChordAdapter(getContext(), chordList);
        recyclerView.setAdapter(chordAdapter);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getActivity(), ChordDetailActivity.class);
                        intent.putExtra("name", chordList.get(position).getName());
                        ArrayList<NoteModel> arraylist = new ArrayList<NoteModel>();
                        for (int i = 0; i < noteList.size(); i++) {
                            if (chordList.get(position).getId() == noteList.get(i).getChord_id()) {
                                arraylist.add(noteList.get(i));
                            }
                        }
                        GuitarCrazy.noteModelArrayList = arraylist;
                        startActivity(intent);
                    }
                }));
    }

    private void fetchData() {
        Call<ResultModel> call = retrofitHelper.getAbChord();
        call.enqueue(new Callback<ResultModel>() {
            @Override
            public void onResponse(Call<ResultModel> call, final Response<ResultModel> response) {
                if (response.isSuccessful()) {
                    final ResultModel result = response.body();
                    list = result.getChord();
                    SetAdapter(list, result.getNote());
                }
            }

            @Override
            public void onFailure(Call<ResultModel> call, Throwable t) {

            }
        });
    }

}