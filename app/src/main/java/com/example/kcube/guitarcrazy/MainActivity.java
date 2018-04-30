package com.example.kcube.guitarcrazy;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.kcube.guitarcrazy.Adapter.ViewPagerIndicatorAdapter;
import com.example.kcube.guitarcrazy.Adapter.ViewpagerAdapter;
import com.example.kcube.guitarcrazy.Model.ResultModel;
import com.example.kcube.guitarcrazy.Other.Database_Handler;
import com.example.kcube.guitarcrazy.Other.LineEditText;
import com.example.kcube.guitarcrazy.Other.RetrofitHelper;
import com.example.kcube.guitarcrazy.tuner.TunerActivity;

import com.example.kcube.guitarcrazy.Adapter.CategoryAdapter;
import com.example.kcube.guitarcrazy.Model.CategoryItemModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemClickListener, ViewPager.OnPageChangeListener {
    private long mpress = 0;
    private static final int delay = 1000;

    boolean isonline = false;

    NavigationView navigationView;
    Menu menu;
    MenuItem navhome;

    private ViewPager intro_images;
    private LinearLayout pager_indicator;
    private int dotsCount;
    private ImageView[] dots;
    private ViewPagerIndicatorAdapter mAdapter;
    GridView gridView;
    CategoryAdapter categoryAdapter;
    ArrayList<CategoryItemModel> arrayList;
    private int[] mImageResources = {
            R.drawable.slide_photo4,
            R.drawable.slide_photo3,
            R.drawable.slid_photo1,
            R.drawable.slide_photo1,
    };

    ProgressDialog pdLoading;
    RetrofitHelper retrofitHelper;
    Database_Handler database_handler;
    Cursor lessoncursor;
    Cursor singercursor;
    Cursor chordcursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

//        ViewPager viewpager = (ViewPager) findViewById(R.id.viewpager);
//        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
//        viewpagerAdapter = new ViewpagerAdapter(getSupportFragmentManager());
//        viewpagerAdapter.addFragment(new ChordCFragment(), "C");
//        viewpagerAdapter.addFragment(new ChordDbFragment(), "C#/Db");
//        viewpagerAdapter.registerDataSetObserver(indicator.getDataSetObserver());
//        viewpager.setAdapter(viewpagerAdapter);
//        indicator.setViewPager(viewpager);

        //viewpager indicator start
        intro_images = (ViewPager) findViewById(R.id.viewpager);

        pager_indicator = (LinearLayout) findViewById(R.id.viewPagerCountDots);

        //get width and height of screen
        DisplayMetrics metric = new DisplayMetrics();
        MainActivity.this.getWindowManager().getDefaultDisplay().getMetrics(metric);
        int heightone = metric.heightPixels;
        int widthone = metric.widthPixels;
        mAdapter = new ViewPagerIndicatorAdapter(MainActivity.this, mImageResources, widthone, widthone);
        intro_images.setAdapter(mAdapter);
        intro_images.setCurrentItem(0);
        intro_images.setOnPageChangeListener(this);
        setUiPageViewController();
//        Handler handler = new Handler();
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

//                boolean back = true;
//                if (intro_images.getCurrentItem() == dotsCount - 1)
//                    back = true;
//                else if (intro_images.getCurrentItem() == 0)
//                    back = false;
//                final boolean finalBack = back;
                MainActivity.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {

                        if (intro_images.getCurrentItem() == dotsCount - 1)
                            intro_images.setCurrentItem(0);
                        else
                            intro_images.setCurrentItem(intro_images.getCurrentItem() + 1);

                    }
                });
            }
        }, 3000, 3000);

//viewpager indicator end


        gridView = (GridView) findViewById(R.id.gview);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        menu = navigationView.getMenu();
        navhome = menu.getItem(0);
        navhome.setChecked(true);

        arrayList = new ArrayList<>();
        arrayList.add(new CategoryItemModel(R.drawable.category1, "Lessons"));
        arrayList.add(new CategoryItemModel(R.drawable.category2, "Lyrics"));
        arrayList.add(new CategoryItemModel(R.drawable.category3, "Chords"));
        arrayList.add(new CategoryItemModel(R.drawable.category4, "Tuner"));
        DisplayMetrics metrics = new DisplayMetrics();
        MainActivity.this.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int height = metrics.heightPixels;
        int width = metrics.widthPixels;
        categoryAdapter = new CategoryAdapter(getApplicationContext(), arrayList, width, height);
        gridView.setAdapter(categoryAdapter);
        gridView.setOnItemClickListener(this);

        pdLoading = new ProgressDialog(MainActivity.this);

        retrofitHelper = new RetrofitHelper();
        database_handler = new Database_Handler(this);

        if (isOnline()) {
            new insertData().execute();
        } else {
            Toast.makeText(this, "No Internect Connection", Toast.LENGTH_SHORT).show();
        }

//        if (lyriccursor.getCount() == 0 || lessoncursor.getCount() == 0 || chordcursor.getCount() == 0) {
//            if (isOnline()) {
////                pdLoading.show();
//
//                insertLesson();
//                insertSingerAndLyric();
//                insertChordAndNote();
//
////                pdLoading.dismiss();
//            } else {
////                Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
//                Snackbar.make(gridView, "No Internet Connection", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
////            if (isOnline()) {
////                insertData();
////            } else {
////                ToaFst.makeText(getApplicationContext(), "Network is not avaliable", Toast.LENGTH_LONG).show();
////            }
//        }
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.side_nav_bar_bg));
//        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.action_bar_bg));

    }

    private boolean isOnline() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }

    class insertData extends AsyncTask<Void, Void, Void> {

//this method will be running on UI thread


        @Override
        protected void onPreExecute() {

            pdLoading.setMessage("\tLoading...");
            pdLoading.setCancelable(false);
            pdLoading.show();

            Log.e("preexecute", "starting");
            lessoncursor = database_handler.getdata("select * from lessons");
            singercursor = database_handler.getdata("select * from singers");
            chordcursor = database_handler.getdata("select * from chords");
        }


        @Override
        protected Void doInBackground(Void... params) {
            if (lessoncursor.getCount() < 1) {
                insertLesson();
            }
            if (singercursor.getCount() < 1) {
                insertSingerAndLyric();
            }
            if (chordcursor.getCount() < 1) {
                insertChordAndNote();
            }
            return null;
        }


        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            pdLoading.dismiss();
        }
    }

    private void insertLesson() {
        //insert lesson data
        Call<ResultModel> lessoncall = retrofitHelper.getLesson();
        lessoncall.enqueue(new Callback<ResultModel>() {
            @Override
            public void onResponse(Call<ResultModel> call, Response<ResultModel> response) {
                if (response.isSuccessful()) {
                    final ResultModel lessons = response.body();
                    for (int i = 0; i < lessons.getLesson().size(); i++) {
                        String title = lessons.getLesson().get(i).getTitle();
                        String content = lessons.getLesson().get(i).getContent();
                        String image = lessons.getLesson().get(i).getImage();
                        database_handler.insertLesson(title, content, image);
                    }
                    Log.e("lesson", "successfully inserted lesson");
                } else {
                    System.out.println(response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<ResultModel> call, Throwable t) {
                Log.e("lesson", "fail inserting");
            }
        });

    }

    private void insertSingerAndLyric() {
        //insert singer and lyric data
        Call<ResultModel> singercall = retrofitHelper.getSinger();
        singercall.enqueue(new Callback<ResultModel>() {
            @Override
            public void onResponse(Call<ResultModel> call, Response<ResultModel> response) {
                if (response.isSuccessful()) {
                    final ResultModel result = response.body();
                    for (int i = 0; i < result.getSinger().size(); i++) {
                        String name = result.getSinger().get(i).getName();
                        database_handler.insertSinger(name);
                    }
                    for (int i = 0; i < result.getLyric().size(); i++) {
                        String title = result.getLyric().get(i).getTitle();
                        String image = result.getLyric().get(i).getImage();
                        int singer_id = result.getLyric().get(i).getSinger_id();
                        database_handler.insertLyric(title, image, singer_id);
                    }
                    Log.e("singer and lyric", "successfully inserted singer and lyric");

                }
            }

            @Override
            public void onFailure(Call<ResultModel> call, Throwable t) {
                Log.e("singer and lyric", "fail inserting");
            }
        });

    }

    private void insertChordAndNote() {
        //insert chord and note data
        Call<ResultModel> result = retrofitHelper.getChord();
        result.enqueue(new Callback<ResultModel>() {
            @Override
            public void onResponse(Call<ResultModel> call, Response<ResultModel> response) {
                if (response.isSuccessful()) {
                    final ResultModel result = response.body();
                    for (int i = 0; i < result.getChord().size(); i++) {
                        String name = result.getChord().get(i).getName();
                        int type = result.getChord().get(i).getType();
                        int count = result.getChord().get(i).getCount();
                        database_handler.insertChord(name, type, count);
                    }
                    for (int i = 0; i < result.getNote().size(); i++) {
                        int fret = result.getNote().get(i).getFret();
                        int string = result.getNote().get(i).getString();
                        int finger = result.getNote().get(i).getFinger();
                        int chord_id = result.getNote().get(i).getChord_id();
                        int chord_count = result.getNote().get(i).getChord_count();
                        database_handler.insertNote(fret, string, finger, chord_id, chord_count);
                    }
                    Log.e("chord and note", "successfully inserted chord and note");
                }
            }

            @Override
            public void onFailure(Call<ResultModel> call, Throwable t) {
                Log.e("chord and note", "fail inserting");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        navhome.setChecked(true);
//        insertData();
    }

    private void setUiPageViewController() {
        dotsCount = mAdapter.getCount();
        dots = new ImageView[dotsCount];

        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(MainActivity.this);
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            params.setMargins(4, 0, 4, 0);

            pager_indicator.addView(dots[i], params);
        }

        dots[0].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (mpress + delay > System.currentTimeMillis()) {
                super.onBackPressed();
                return;
            } else if (mpress + delay < System.currentTimeMillis()) {
                Toast.makeText(getBaseContext(),
                        "Press again to exit",
                        Toast.LENGTH_SHORT).show();

            }
        }
        mpress = System.currentTimeMillis();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_feedback) {
            feedbackalert();
            return true;
        } else if (id == R.id.action_about) {
            Intent aboutintent = new Intent(this, AboutActivity.class);
            startActivity(aboutintent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {

        } else if (id == R.id.nav_favourite) {
            Intent favintent = new Intent(this, FavouriteActivity.class);
            startActivity(favintent);
        } else if (id == R.id.nav_send) {
            feedbackalert();
        } else if (id == R.id.nav_about) {
            Intent aboutintent = new Intent(this, AboutActivity.class);
            startActivity(aboutintent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    private void feedbackalert() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = this.getLayoutInflater().inflate(R.layout.feedback_alert, null);
        builder.setTitle("Sent your feedback")
                .setView(view);

        final LineEditText lineEditText = (LineEditText) view.findViewById(R.id.lineEditText);
        builder.setPositiveButton("Send", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL, new String[]{"sawkyaw1111.sk1@gmail.com"});
                i.putExtra(Intent.EXTRA_SUBJECT, "Feedback for guitar crazy application");
                i.putExtra(Intent.EXTRA_TEXT, lineEditText.getText().toString());
                try {
                    startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(MainActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                Intent lessonintent = new Intent(this, LessonActivity.class);
                startActivity(lessonintent);
                break;
            case 1:
                Intent lyricintent = new Intent(this, LyricActivity.class);
                startActivity(lyricintent);
                break;
            case 2:
                Intent chordintent = new Intent(this, ChordActivity.class);
                startActivity(chordintent);
                break;
            case 3:
                Intent tunerintent = new Intent(this, TunerActivity.class);
                startActivity(tunerintent);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < dotsCount; i++) {
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem_dot));
        }

        dots[position].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
