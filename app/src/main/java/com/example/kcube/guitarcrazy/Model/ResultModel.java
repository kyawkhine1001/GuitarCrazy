package com.example.kcube.guitarcrazy.Model;

import java.util.ArrayList;

/**
 * Created by K Cube on 10/24/2017.
 */

public class ResultModel {
    private int status;
    private String message;

    private ArrayList<LessonModel> lesson;
    private ArrayList<ChordModel> chord;
    private ArrayList<NoteModel> note;
    private ArrayList<SingerModel> singer;
    private ArrayList<LyricModel> lyric;

    public ArrayList<LyricModel> getLyric() {
        return lyric;
    }

    public ArrayList<SingerModel> getSinger() {
        return singer;
    }

    public ArrayList<NoteModel> getNote() {
        return note;
    }

    public ArrayList<ChordModel> getChord() {
        return chord;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<LessonModel> getLesson() {
        return lesson;
    }
}
