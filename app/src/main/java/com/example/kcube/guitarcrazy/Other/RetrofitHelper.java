package com.example.kcube.guitarcrazy.Other;

import com.example.kcube.guitarcrazy.Model.ResultModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.CookieManager;
import java.net.CookiePolicy;

import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by K Cube on 10/20/2017.
 */
public class RetrofitHelper {
    private ApiService apiService;
    //    static final String BASE_URL = "http://192.168.43.32/Guitar_Crazy/public/api/";
//    public static final String PHOTO_BASE_URL = "http://192.168.43.32/Guitar_Crazy/public/backend/upload/";

    private static final String BASE_URL = "https://guitarcrazy.000webhostapp.com/api/";
    public static final String PHOTO_BASE_URL = "https://guitarcrazy.000webhostapp.com/backend/upload/";

    public RetrofitHelper() {
        Gson gson = new GsonBuilder().create();

        CookieManager cookieManager = new CookieManager();
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cookieJar(new JavaNetCookieJar(cookieManager))
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .callFactory(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        apiService = retrofit.create(ApiService.class);

    }

    public Call<ResultModel> getLesson() {
        return apiService.getLesson();
    }

    public Call<ResultModel> getSinger() {
        return apiService.getSinger();
    }

    public Call<ResultModel> getLyric() {
        return apiService.getLyric();
    }

    public Call<ResultModel> getChord() {
        return apiService.getChord();
    }

    public Call<ResultModel> getCChord() {
        return apiService.getcchord();
    }

    public Call<ResultModel> getDbChord() {
        return apiService.getdbchord();
    }

    public Call<ResultModel> getDChord() {
        return apiService.getdchord();
    }

    public Call<ResultModel> getEbChord() {
        return apiService.getebchord();
    }

    public Call<ResultModel> getEChord() {
        return apiService.getechord();
    }

    public Call<ResultModel> getFChord() {
        return apiService.getfchord();
    }

    public Call<ResultModel> getGbChord() {
        return apiService.getgbchord();
    }

    public Call<ResultModel> getGChord() {
        return apiService.getgchord();
    }

    public Call<ResultModel> getAbChord() {
        return apiService.getabchord();
    }

    public Call<ResultModel> getAChord() {
        return apiService.getachord();
    }

    public Call<ResultModel> getBbChord() {
        return apiService.getbbchord();
    }

    public Call<ResultModel> getBChord() {
        return apiService.getbchord();
    }
}
