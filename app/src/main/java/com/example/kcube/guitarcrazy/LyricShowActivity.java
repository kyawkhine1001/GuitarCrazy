package com.example.kcube.guitarcrazy;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.kcube.guitarcrazy.Other.Database_Handler;
import com.jsibbold.zoomage.ZoomageView;

import java.io.IOException;

import de.ailis.pherialize.MixedArray;
import de.ailis.pherialize.Pherialize;

import static com.example.kcube.guitarcrazy.Other.RetrofitHelper.PHOTO_BASE_URL;

public class LyricShowActivity extends AppCompatActivity {
    ZoomageView lyricShow;
    int lyricid;
    String fav = "false";
    String favname = "false";
    MixedArray imagearray;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lyric_show);
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.action_bar_bg));
        lyricid = GuitarCrazy.sentLyricModel.getId();
        String title = GuitarCrazy.sentLyricModel.getTitle();
        String image = GuitarCrazy.sentLyricModel.getImage();
        fav = GuitarCrazy.sentLyricModel.getFav();
        favname = fav;
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        lyricShow = (ZoomageView) findViewById(R.id.lyricShow);
        imagearray = Pherialize.unserialize(image).toArray();
//        Bitmap m_d1 = BitmapFactory.decodeResource(getResources(), R.drawable.pleasewait);
//        DisplayMetrics metric = new DisplayMetrics();
//        LyricShowActivity.this.getWindowManager().getDefaultDisplay().getMetrics(metric);
//        int height = metric.heightPixels;
//        int width = metric.widthPixels;
//        Bitmap resizedBitmap = Bitmap.createScaledBitmap(m_d1, width, height,
//                true);
//        Drawable resizedDrawable = new BitmapDrawable(getResources(), resizedBitmap);
        try {
            Glide.with(LyricShowActivity.this)
                    .load(PHOTO_BASE_URL + imagearray.getString(0))
                    .into(lyricShow);
        } catch (Exception e) {
            Log.e("Glide error", e.toString());
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.lyric_menu, menu);
        if (fav.equals("0")) {
            menu.getItem(0).setIcon(R.drawable.heart);
        } else if (fav.equals("1")) {
            menu.getItem(0).setIcon(R.drawable.heartoutline);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_fav) {
            if (favname.equals("0")) {
                new Database_Handler(this).upgradeFav("lyrics", lyricid, "1");
                favname = "1";
                item.setIcon(R.drawable.heartoutline);
                Toast.makeText(this, "Add to favourite", Toast.LENGTH_SHORT).show();
            } else if (favname.equals("1")) {
                new Database_Handler(this).upgradeFav("lyrics", lyricid, "0");
                favname = "0";
                item.setIcon(R.drawable.heart);
                Toast.makeText(this, "Remove from favourite", Toast.LENGTH_SHORT).show();
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
