package com.letap.learnjava.poetryterms;

/**
 * Created by Dhvani on 31/10/2015.
 */

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class HomeFragment extends Fragment {

    public HomeFragment() {
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        final ImageView cling = (ImageView)rootView.findViewById(R.id.imageView);
       // final ImageView cling_pressed = (ImageView)rootView.findViewById(R.id.imageView2);

        if(MainActivity.display == true){

            cling.setVisibility(View.VISIBLE);

            rootView.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {

                    if(event.getAction() == MotionEvent.ACTION_DOWN){
                        //do something
                        cling.setVisibility(View.GONE);
                        MainActivity.display = false;

                    }
                    return true;
                }
            });
        }




        return rootView;
    }

}