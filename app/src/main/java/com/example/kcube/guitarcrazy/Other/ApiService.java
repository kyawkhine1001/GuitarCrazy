package com.example.kcube.guitarcrazy.Other;

import com.example.kcube.guitarcrazy.Model.ResultModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by K Cube on 10/20/2017.
 */
public interface ApiService {

    @GET("lesson")
    Call<ResultModel> getLesson();


    @GET("singer")
    Call<ResultModel> getSinger();

    @GET("lyric")
    Call<ResultModel> getLyric();

    @GET("chord")
    Call<ResultModel> getChord();

    @GET("cchord")
    Call<ResultModel> getcchord();

    @GET("dbchord")
    Call<ResultModel> getdbchord();

    @GET("dchord")
    Call<ResultModel> getdchord();

    @GET("ebchord")
    Call<ResultModel> getebchord();

    @GET("echord")
    Call<ResultModel> getechord();

    @GET("fchord")
    Call<ResultModel> getfchord();

    @GET("gbchord")
    Call<ResultModel> getgbchord();

    @GET("gchord")
    Call<ResultModel> getgchord();

    @GET("abchord")
    Call<ResultModel> getabchord();

    @GET("achord")
    Call<ResultModel> getachord();

    @GET("bbchord")
    Call<ResultModel> getbbchord();

    @GET("bchord")
    Call<ResultModel> getbchord();
}
