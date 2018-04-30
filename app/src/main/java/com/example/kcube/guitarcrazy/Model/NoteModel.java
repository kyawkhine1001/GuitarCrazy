package com.example.kcube.guitarcrazy.Model;

/**
 * Created by K Cube on 10/25/2017.
 */

public class NoteModel {
    private int fret, string, finger, chord_id, chord_count;

    public NoteModel(int fret, int string, int finger, int chord_id) {
        this.fret = fret;
        this.string = string;
        this.finger = finger;
        this.chord_id = chord_id;
    }

    public NoteModel(int fret, int string, int finger) {
        this.fret = fret;
        this.string = string;
        this.finger = finger;

    }

    public int getFret() {
        return fret;
    }

    public int getString() {
        return string;
    }

    public int getFinger() {
        return finger;
    }

    public int getChord_id() {
        return chord_id;
    }

    public int getChord_count() {
        return chord_count;
    }
}
