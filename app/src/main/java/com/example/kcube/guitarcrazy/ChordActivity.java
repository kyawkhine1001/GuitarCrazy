package com.example.kcube.guitarcrazy;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.kcube.guitarcrazy.Adapter.ViewpagerAdapter;
import com.example.kcube.guitarcrazy.Model.ResultModel;
import com.example.kcube.guitarcrazy.Other.Database_Handler;
import com.example.kcube.guitarcrazy.Other.RetrofitHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChordActivity extends AppCompatActivity {
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewpagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chord);
        toolbar = (Toolbar) findViewById(R.id.tbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.action_bar_bg));
        getSupportActionBar().setTitle("Chords");
        tabLayout = (TabLayout) findViewById(R.id.tlayout);
        viewPager = (ViewPager) findViewById(R.id.vpager);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setBackgroundDrawable(getResources().getDrawable(R.drawable.action_bar_bg));

    }


    public void setupViewPager(ViewPager vp) {
        adapter = new ViewpagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ChordCFragment(), "C");
        adapter.addFragment(new ChordDbFragment(), "C#/Db");
        adapter.addFragment(new ChordDFragment(), "D");
        adapter.addFragment(new ChordEbFragment(), "D#/Eb");
        adapter.addFragment(new ChordEFragment(), "E");
        adapter.addFragment(new ChordFFragment(), "F");
        adapter.addFragment(new ChordGbFragment(), "F#/Gb");
        adapter.addFragment(new ChordGFragment(), "G");
        adapter.addFragment(new ChordAbFragment(), "G#/Ab");
        adapter.addFragment(new ChordAFragment(), "A");
        adapter.addFragment(new ChordBbFragment(), "A#/Bb");
        adapter.addFragment(new ChordBFragment(), "B");
        vp.setAdapter(adapter);

    }
}
