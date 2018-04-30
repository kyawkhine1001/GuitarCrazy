package com.example.kcube.guitarcrazy;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.kcube.guitarcrazy.Model.NoteModel;
import com.example.kcube.guitarcrazy.Other.GuitarChordView;
import com.example.kcube.guitarcrazy.Other.RetrofitHelper;
import com.example.kcube.guitarcrazy.R;

import java.util.ArrayList;

public class ChordDetailActivity extends AppCompatActivity {
    GuitarChordView chordView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chord_detail);
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.action_bar_bg));
        getSupportActionBar().setTitle(getIntent().getStringExtra("name"));
        chordView = (GuitarChordView) findViewById(R.id.chord_view);
//        chordView.setBackground(getResources().getDrawable(R.drawable.side_nav_bar_bg));
        chordView.setNoteColor(getResources().getColor(R.color.colorPrimaryDark));
        chordView.setNoteNumberColor(getResources().getColor(android.R.color.white));
//        chordView.setBarLineColor(getColor(R.color.colorAccent));

        chordView.setFretMarkerColor(getResources().getColor(android.R.color.white));//fret
        chordView.setStringColor(getResources().getColor(android.R.color.white));//string

        chordView.setFretNumberColor(getResources().getColor(R.color.colorPrimaryDark));//1-to-12
        chordView.setStringMarkerColor(getResources().getColor(R.color.colorPrimaryDark));//0-=>6

        fetchData();

    }

    private void fetchData() {
        ArrayList<NoteModel> arrayList = GuitarCrazy.noteModelArrayList;
        for (int i = 0; i < arrayList.size(); i++) {
            chordView.addNote(arrayList.get(i).getFret(), arrayList.get(i).getString(), arrayList.get(i).getFinger());
        }
//        chordView.addNote(1, 2, 1);
//        chordView.addNote(2, 4, 2);
//        chordView.addNote(3, 5, 3);
    }
}
