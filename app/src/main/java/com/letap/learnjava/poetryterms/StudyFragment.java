package com.letap.learnjava.poetryterms;




import android.content.Intent;
import android.os.Bundle;


import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * Created by Dhvani on 07/12/2015.
 */
public class StudyFragment extends Fragment {

    public StudyFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View rootView = inflater.inflate(R.layout.fragment_study, container, false);

       rootView.findViewById(R.id.study).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity().getApplicationContext(), StudyActivity.class);
                startActivity(intent);

            }
        });



        return rootView;
    }


    }
