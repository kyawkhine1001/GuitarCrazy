package com.example.kcube.guitarcrazy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SearchSingerActivity extends AppCompatActivity {
    Toolbar toolbar;
    MenuItem mdelete;
    boolean isSearchOpened = false;
    AutoCompleteTextView edtSearch;
    ListView listView1, listView2;
    ArrayList<String> arrayList;
    ArrayAdapter<String> adapter;
    //    ArrayList<SearchItemGenerator> sentarrayList;
    //    SearchCursorAdapter adapter;
    Cursor cursor, secondcursor, thirdcursor, fourthcursor;
    Cursor city_history_cursor, pyu_pagoda_cursor, pyu_place_cursor, pyay_pagoda_cursor, pyay_place_cursor, pyay_other_cursor, atm_cursor, rice_cursor, medical_shop_cursor, office_cursor;
    Cursor hotel_cursor, motel_cursor, guesthouse_cursor, restaurant_cursor, cafe_cursor, coldandcoffee_cursor, bank_cursor, car_cursor, train_cursor, hospital_cursor, clinic_cursor;
    Cursor uni_cursor, college_cursor, school_cursor, preschool, guide;
    Cursor computerandmobile_cursor, gold_cursor, electronic_cursor, childfood_cursor, beatuysalon_cursor, trader_cursor,
            store_cursor, clothesshop_cursor, cycleandcar_cursor, garage_cursor, forhome_cursor, farm_cursor, footwear_curosr, company_cursor, forstudent_cursor, myatman_cursor, thingan_cursor, tailor_cursor;
    String table, name, address, phno, desp, lat, lng, fav, pic1, pic2, pic3;
    int getIndex, id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_singer);
        handleMenuSarch();
        listView1 = (ListView) findViewById(R.id.listview);

        arrayList = new ArrayList<>();
        arrayList.add("ao KO");
        arrayList.add("b KO");
        arrayList.add("co KO");
        arrayList.add("do KO");
        arrayList.add("eo KO");
        arrayList.add("fo KO");
        arrayList.add("Ko KO");

        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,arrayList);
        listView1.setAdapter(adapter);
//
//        sentarrayList = new ArrayList<>();
//
//
//        tailor_cursor = new Databasehandler(getApplicationContext()).getdata("select * from tailor ");
//
//        sentarrayList = new ArrayList<>();
//
//
//        tailor_cursor.moveToFirst();
//        while (!tailor_cursor.isAfterLast()) {
//            sentarrayList.add(new SearchItemGenerator(tailor_cursor.getInt(0), tailor_cursor.getString(1), tailor_cursor.getString(2), tailor_cursor.getString(3), tailor_cursor.getString(4), tailor_cursor.getString(5), tailor_cursor.getString(6), tailor_cursor.getString(7), tailor_cursor.getString(8), tailor_cursor.getString(9), tailor_cursor.getString(10), "showhotel", "tailor"));
//            tailor_cursor.moveToNext();
//        }

        //fontchange
//        Typeface typeface;
//        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("uni_zaw", 01);
//        if (sharedPreferences.getInt("uni_zaw", 0) == 0) {
//            typeface = Typeface.createFromAsset(getApplicationContext().getAssets(), "zawgyi.ttf");
//            for (int i = 0; i < sentarrayList.size(); i++) {
//                arrayList.add(sentarrayList.get(i).getName());
//
//            }
//        } else if (sharedPreferences.getInt("uni_zaw", 1) == 1) {
//            typeface = Typeface.createFromAsset(getApplicationContext().getAssets(), "unicode.ttf");
//            for (int i = 0; i < sentarrayList.size(); i++) {
//                arrayList.add(new uni_zaw_change().zg2uni(sentarrayList.get(i).getName()));
//
//            }
//
//        }

//        adapter = new ArrayAdapter<String>(SearchSingerActivity.this, android.R.layout.simple_list_item_1, arrayList);
//        listView1.setAdapter(adapter);
//        listView1.setOnItemClickListener(this);
        // listView2.setAdapter(adapter);


    }

//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        Intent showhistory = new Intent(SearchActivity.this, ShowHistoryActivity.class);
//        Intent showaddress = new Intent(SearchActivity.this, ShowaddressActivity.class);
//        Intent showhotel = new Intent(SearchActivity.this, ShowhotelActivity.class);
//
//        // this sentence will be same search
//
//        getIndex = arrayList.indexOf(adapter.getItem(position));
//
//        table = sentarrayList.get(getIndex).getTable();
//        id = sentarrayList.get(getIndex).getId();
//        name = sentarrayList.get(getIndex).getName();
//        address = sentarrayList.get(getIndex).getAddress();
//        phno = sentarrayList.get(getIndex).getPhno();
//        desp = sentarrayList.get(getIndex).getDesp();
//        lat = sentarrayList.get(getIndex).getLat();
//        lng = sentarrayList.get(getIndex).getLng();
//        fav = sentarrayList.get(getIndex).getFav();
//        pic1 = sentarrayList.get(getIndex).getPic1();
//        pic2 = sentarrayList.get(getIndex).getPic2();
//        pic3 = sentarrayList.get(getIndex).getPic3();
//        //Toast.makeText(getApplicationContext(), sentarrayList.get(getIndex).getActivity()+"", Toast.LENGTH_SHORT).show();
//        switch (sentarrayList.get(getIndex).getActivity()) {
//            case "showhotel":
//                Log.e("name is ", name);
//                showhotel.putExtra("table", table);
//                showhotel.putExtra("id", sentarrayList.get(getIndex).getId());
//                showhotel.putExtra("name", name);
//                showhotel.putExtra("address", address);
//                showhotel.putExtra("phno", phno);
//                showhotel.putExtra("desp", desp);
//                showhotel.putExtra("lat", lat);
//                showhotel.putExtra("lng", lng);
//                showhotel.putExtra("fav", fav);
//                showhotel.putExtra("pic1", pic1);
//                showhotel.putExtra("pic2", pic2);
//                showhotel.putExtra("pic3", pic3);
//                startActivity(showhotel);
//                break;
//            case "showhistory":
//                Log.e("name is ", name);
//                showhistory.putExtra("table", table);
//                showhistory.putExtra("id", sentarrayList.get(getIndex).getId());
//                showhistory.putExtra("name", name);
////            showhotel.putExtra("address", address);
////            showhotel.putExtra("phno", phno);
//                showhistory.putExtra("desp", desp);
//                showhistory.putExtra("lat", lat);
//                showhistory.putExtra("lng", lng);
//                showhistory.putExtra("fav", fav);
//                showhistory.putExtra("pic1", pic1);
//                showhistory.putExtra("pic2", pic2);
//                showhistory.putExtra("pic3", pic3);
//                startActivity(showhistory);
//                break;
//            case "showaddress":
//                Log.e("name is ", name);
//                showaddress.putExtra("table", table);
//                showaddress.putExtra("id", sentarrayList.get(getIndex).getId());
//                showaddress.putExtra("name", name);
////            showhotel.putExtra("address", address);
////            showhotel.putExtra("phno", phno);
//                showaddress.putExtra("desp", desp);
//                showaddress.putExtra("lat", lat);
//                showaddress.putExtra("lng", lng);
//                showaddress.putExtra("fav", fav);
//                showaddress.putExtra("pic1", pic1);
//                showaddress.putExtra("pic2", pic2);
//                showaddress.putExtra("pic3", pic3);
//                startActivity(showaddress);
//                break;
//            }
//
//        }

    //    @Override
//    public boolean onPrepareOptionsMenu(Menu menu) {
//        mSearchAction = menu.findItem(R.id.search);
//        return super.onPrepareOptionsMenu(menu);
//    }

    private void handleMenuSarch() {
        final ActionBar action = getSupportActionBar();
//        if(isSearchOpened){
//            action.setDisplayShowCustomEnabled(false);
//            action.setDisplayShowTitleEnabled(true);
//            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
//            imm.hideSoftInputFromWindow(edtSearch.getWindowToken(),0);
//            mSearchAction.setIcon(getResources().getDrawable(android.R.drawable.ic_menu_search));
//            isSearchOpened = false;
//        } else {
        action.setDisplayShowCustomEnabled(true);
        action.setCustomView(R.layout.search_bar);
        action.setBackgroundDrawable(getResources().getDrawable(R.drawable.action_bar_bg));
        action.setDisplayHomeAsUpEnabled(true);
        action.setDisplayShowTitleEnabled(false);
        edtSearch = (AutoCompleteTextView) action.getCustomView().findViewById(R.id.edtSearch);
        edtSearch.requestFocus();
        edtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    doSearch();
                    return true;
                }
                return false;
            }

        });
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() <= 0) {
                    SearchSingerActivity.this.adapter.getFilter().filter(s);
                    mdelete.setVisible(false);
                } else {
                    SearchSingerActivity.this.adapter.getFilter().filter(s);
                    mdelete.setVisible(true);
                }

                // action.setIcon(getResources().getDrawable(android.R.drawable.ic_menu_close_clear_cancel));
                //  getIndex = SearchActivity.this.adapter.getPosition(start);
                //SearchActivity.this.listView1.setAdapter(new SearchCursorAdapter(getApplicationContext(),sentarrayList));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(edtSearch, InputMethodManager.SHOW_IMPLICIT);
//        action.setIcon(getResources().getDrawable(android.R.drawable.ic_menu_close_clear_cancel));
        isSearchOpened = true;
    }
    //  }

    private void doSearch() {
        Toast.makeText(getApplicationContext(), edtSearch.getText().toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.delete, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        mdelete = menu.findItem(R.id.delete);
        mdelete.setVisible(false);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete:
                edtSearch.setText("");
                break;
        }
        return super.onOptionsItemSelected(item);
    }
//    @Override
//    public void onBackPressed() {
//        if(isSearchOpened){
//            handleMenuSarch();
//            return;
//        }
//        super.onBackPressed();
//    }
}
