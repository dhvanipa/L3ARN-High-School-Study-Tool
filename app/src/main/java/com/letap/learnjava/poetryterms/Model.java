package com.letap.learnjava.poetryterms;

/**
 * Created by Dhvani on 29/11/2015.
 */
public class Model {

    private int icon;
    private String title;
   // private String counter;

   private boolean isGroupHeader = true;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Model(int icon, String title) {
        super();
        this.icon = icon;
        this.title = title;
       //  this.counter = counter;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
