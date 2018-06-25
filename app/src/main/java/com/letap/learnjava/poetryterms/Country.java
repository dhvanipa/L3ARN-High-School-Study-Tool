package com.letap.learnjava.poetryterms;

/**
 * Created by Dhvani on 04/12/2015.
 */

public class Country {


    private String name = "";


    public Country(String name) {
        super();

        this.name = name;

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString(){
        return getName();
    }



}
