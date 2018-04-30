package com.example.kcube.guitarcrazy.Model;

/**
 * Created by K Cube on 10/24/2017.
 */

public class LessonModel {
    private String title;
    private String content;
    private String image;

    public LessonModel(String title, String content, String image) {
        this.title = title;
        this.content = content;
        this.image = image;

    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getImage() {
        return image;
    }
}
