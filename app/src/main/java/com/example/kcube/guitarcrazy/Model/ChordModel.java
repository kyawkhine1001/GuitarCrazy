package com.example.kcube.guitarcrazy.Model;

/**
 * Created by K Cube on 10/25/2017.
 */

public class ChordModel {
    private int id;
    private String name;
    private int type, count;

    public ChordModel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }

    public int getCount() {
        return count;
    }
}
