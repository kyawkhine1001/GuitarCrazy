package com.example.kcube.guitarcrazy.Other;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.kcube.guitarcrazy.Model.NoteModel;

import java.util.ArrayList;

/**
 * Created by K Cube on 10/26/2017.
 */
public class Database_Handler extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "guitar_crazy";
    SQLiteDatabase db = Database_Handler.this.getWritableDatabase();

    private static final String LESSON_TABLE = "lessons",
            LESSON_ID = "id",
            LESSON_TITLE = "title",
            LESSON_CONTENT = "content",
            LESSON_IMAGE = "image",
            LESSON_FAV = "fav";

    private static final String SINGER_TABLE = "singers",
            SINGER_ID = "id",
            SINGER_NAME = "name";

    private static final String LYRIC_TABLE = "lyrics",
            LYRIC_ID = "id",
            LYRIC_TITLE = "title",
            LYRIC_IMAGE = "image",
            LYRIC_SINGER_ID = "singer_id",
            LYRIC_FAV = "fav";

    private static final String CHORD_TABLE = "chords",
            CHORD_ID = "id",
            CHORD_NAME = "name",
            CHORD_TYPE = "type",
            CHORD_COUNT = "count";

    private static final String NOTE_TABLE = "notes",
            NOTE_ID = "id",
            NOTE_FRET = "fret",
            NOTE_STRING = "string",
            NOTE_FINGER = "finger",
            NOTE_CHORD_ID = "chord_id",
            NOTE_CHORD_COUNT = "chord_count";


    public Database_Handler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_LESSON_TABLE = "CREATE TABLE " + LESSON_TABLE + "("
                + LESSON_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + LESSON_TITLE + " TEXT ,"
                + LESSON_CONTENT + " TEXT,"
                + LESSON_IMAGE + " TEXT,"
                + LESSON_FAV + " TEXT )";
        db.execSQL(CREATE_LESSON_TABLE);

        String CREATE_SINGER_TABLE = "CREATE TABLE " + SINGER_TABLE + "("
                + SINGER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + SINGER_NAME + " TEXT )";
        db.execSQL(CREATE_SINGER_TABLE);

        String CREATE_LYRIC_TABLE = "CREATE TABLE " + LYRIC_TABLE + "("
                + LYRIC_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + LYRIC_TITLE + " TEXT ,"
                + LYRIC_IMAGE + " TEXT,"
                + LYRIC_SINGER_ID + " INTEGER ,"
                + LYRIC_FAV + " TEXT )";
        db.execSQL(CREATE_LYRIC_TABLE);

        String CREATE_CHORD_TABLE = "CREATE TABLE " + CHORD_TABLE + "("
                + CHORD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + CHORD_NAME + " TEXT ,"
                + CHORD_TYPE + " TEXT ,"
                + CHORD_COUNT + " TEXT )";
        db.execSQL(CREATE_CHORD_TABLE);

        String CREATE_NOTE_TABLE = "CREATE TABLE " + NOTE_TABLE + "("
                + NOTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + NOTE_FRET + " INTEGER ,"
                + NOTE_STRING + " INTEGER ,"
                + NOTE_FINGER + " INTEGER ,"
                + NOTE_CHORD_ID + " INTEGER ,"
                + NOTE_CHORD_COUNT + " INTEGER )";
        db.execSQL(CREATE_NOTE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + LESSON_TABLE);
        onCreate(db);

    }

    public Cursor getdata(String query) {
        Cursor cursor = db.rawQuery(query, null);

        Log.i("Select Query : ", query);
        Log.i("database", db + "");
        Log.i("Cursor is :" + cursor + "", query);
        Log.i("Cursor count :", " " + Integer.toString(cursor.getCount()));

        return cursor;
    }

    public void insertLesson(String title, String content, String image) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", title);
        contentValues.put("content", content);
        contentValues.put("image", image);
        contentValues.put("fav", "0");
        db.insert("lessons", null, contentValues);
    }

    public void insertSinger(String name) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        db.insert("singers", null, contentValues);
    }

    public void insertLyric(String title, String image, int singer_id) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", title);
        contentValues.put("image", image);
        contentValues.put("singer_id", singer_id);
        contentValues.put("fav", "0");
        db.insert("lyrics", null, contentValues);
    }

    public void insertChord(String name, int type, int count) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("type", type);
        contentValues.put("count", count);
        db.insert("chords", null, contentValues);
    }

    public void insertNote(int fret, int string, int finger, int chord_id, int chord_count) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("fret", fret);
        contentValues.put("string", string);
        contentValues.put("finger", finger);
        contentValues.put("chord_id", chord_id);
        contentValues.put("chord_count", chord_count);
        db.insert("notes", null, contentValues);
    }

    public void upgradeFav(String table, int id, String star) {
        db = this.getReadableDatabase();
//        ContentValues c = new ContentValues();
//        c.put("fav", star);
//        db.update(table, c, "id = '" + id + "'", null);
        String updatequery = "update " + table + " set fav = '" + star + "'" + " where id = '" + id + "'";
        db.execSQL(updatequery);
        Log.e("executed", updatequery);
    }
//    public int update_content(String content, int id) {
//        Log.d("DATA", content + " and " + id);
//        SQLiteDatabase db = this.getReadableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(KEY_CONTENT, content);
//        return db.update(KEY_USER_TABLE, values, KEY_ID + "='" + id + "'", null);
//
//    }
}
