package com.example.kcube.guitarcrazy.Model;

/**
 * Created by K Cube on 10/25/2017.
 */

public class LyricModel {
    private int id;
    private String title;
    private String image;
    private int singer_id;
    private String fav;


    public LyricModel(int id, String title, String image, int singer_id, String fav) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.singer_id = singer_id;
        this.fav = fav;
    }

    public LyricModel(String title, String image, int singer_id, String fav) {
        this.title = title;
        this.image = image;
        this.singer_id = singer_id;
        this.fav = fav;
    }

    public LyricModel(int id, String title, String image, int singer_id) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.singer_id = singer_id;
    }

    public int getId() {
        return id;
    }

    public int getSinger_id() {
        return singer_id;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public String getFav() {
        return fav;
    }
}
