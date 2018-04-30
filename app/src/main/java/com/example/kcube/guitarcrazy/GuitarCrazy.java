package com.example.kcube.guitarcrazy;

import android.app.Application;

import com.example.kcube.guitarcrazy.Model.LyricModel;
import com.example.kcube.guitarcrazy.Model.NoteModel;
import com.example.kcube.guitarcrazy.Model.SingerModel;

import java.util.ArrayList;

/**
 * Created by K Cube on 10/25/2017.
 */

public class GuitarCrazy extends Application {
    public static ArrayList<NoteModel> noteModelArrayList;
    public static ArrayList<LyricModel> lyricModelArrayList;
    public static SingerModel sentSingerModel;
    public static LyricModel sentLyricModel;
}
