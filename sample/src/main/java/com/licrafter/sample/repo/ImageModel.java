package com.licrafter.sample.repo;

import java.io.Serializable;

/**
 * author: shell
 * date 2017/1/16 上午11:38
 **/
public class ImageModel implements Serializable{

    private String url;
    private int height;
    private int width;

    public ImageModel(String url, int width, int height) {
        this.url = url;
        this.height = height;
        this.width = width;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
