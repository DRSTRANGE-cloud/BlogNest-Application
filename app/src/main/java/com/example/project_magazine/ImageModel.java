package com.example.project_magazine;

import android.graphics.Bitmap;

public class ImageModel {
    private String imageName;
    private Bitmap imageBitMap;

    public ImageModel(String imageName, Bitmap imageBitMap) {
        this.imageName = imageName;
        this.imageBitMap = imageBitMap;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Bitmap getImageBitMap() {
        return imageBitMap;
    }

    public void setImageBitMap(Bitmap imageBitMap) {
        this.imageBitMap = imageBitMap;
    }
}
