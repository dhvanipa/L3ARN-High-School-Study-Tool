package com.letap.learnjava.poetryterms;

/**
 * Created by dhvani on 2016-06-05.
 */

public class Movie {
    private String title, thumbnailUrl, desc;
    int id;

    public Movie() {
    }

    public Movie(int id, String name, String thumbnailUrl, String desc) {
        this.id = id;
        this.title = name;
        this.thumbnailUrl = thumbnailUrl;
        this.desc = desc;
    }

    public Movie(String name, String thumbnailUrl, String desc) {
        this.title = name;
        this.thumbnailUrl = thumbnailUrl;
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setTitle(String name) {
        this.title = name;
    }
    // getting ID
    public int getID(){
        return this.id;
    }

    // setting id
    public void setID(int id){
        this.id = id;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }




}