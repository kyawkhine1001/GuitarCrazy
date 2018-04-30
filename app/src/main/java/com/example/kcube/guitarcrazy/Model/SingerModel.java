package com.example.kcube.guitarcrazy.Model;

/**
 * Created by K Cube on 10/25/2017.
 */

public class SingerModel {
    private int id;
    private String name;

    public SingerModel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public SingerModel(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
