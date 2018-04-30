package com.example.kcube.guitarcrazy.Model;

import android.graphics.Bitmap;

/**
 * Created by K Cube on 10/17/2017.
 */
public class CategoryItemModel {
    Integer image;
    String text;

    public CategoryItemModel(Integer image, String text) {
        this.image = image;
        this.text = text;

    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
