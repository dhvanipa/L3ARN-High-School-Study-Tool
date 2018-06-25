package com.letap.learnjava.poetryterms;

/**
 * Created by dhvani on 2016-06-05.
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.provider.SyncStateContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

public class CustomListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Movie> movieItems;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    private Bitmap bitmap;

    public CustomListAdapter(Activity activity, List<Movie> movieItems) {
        this.activity = activity;
        this.movieItems = movieItems;
    }

    @Override
    public int getCount() {
        return movieItems.size();
    }

    @Override
    public Object getItem(int location) {
        return movieItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_item, null);


        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
       // ImageView thumbNail = (ImageView) convertView
         //       .findViewById(R.id.thumbnail);
        TextView title = (TextView) convertView.findViewById(R.id.title);
       //  TextView rating = (TextView) convertView.findViewById(R.id.rating);
        // TextView genre = (TextView) convertView.findViewById(R.id.genre);
        // TextView year = (TextView) convertView.findViewById(R.id.releaseYear);

        // getting movie data for the row
        Movie m = movieItems.get(position);

        // thumbnail image

        //  Bitmap bitmap = loadImage(m.getThumbnailUrl());
        // System.out.println(m.getThumbnailUrl());
        //  new AsyncCaller(m.getThumbnailUrl()).execute();
        if(m.getThumbnailUrl() != null) {
            File newFile = new File(m.getThumbnailUrl());


            if (newFile.exists()) {
                System.out.println("here");
               // bitmap = BitmapFactory.decodeFile(newFile.getAbsolutePath());
              //  thumbNail.setImageBitmap(bitmap);
            }
            System.out.println(m.getThumbnailUrl());
        }

        // title
        title.setText(m.getTitle());
        // rating.setText(m.getDesc());
/*
        // rating
        rating.setText("Rating: " + String.valueOf(m.getRating()));

        // genre
        String genreStr = "";
        for (String str : m.getGenre()) {
            genreStr += str + ", ";
        }
        genreStr = genreStr.length() > 0 ? genreStr.substring(0,
                genreStr.length() - 2) : genreStr;
        genre.setText(genreStr);

        // release year
        year.setText(String.valueOf(m.getYear()));
        */

        return convertView;
    }





}
