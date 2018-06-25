package com.letap.learnjava.poetryterms;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Dhvani on 27/11/2015.
 */
public class QuizzesFragment extends Fragment {

    List<String> itemList;
    List<Integer> itemPics;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final RecyclerView recyclerView = (RecyclerView) inflater.inflate(
                R.layout.fragment_quizzes, container, false);
        RecycleAdapter.pos = -1;
        setupRecyclerView(recyclerView);
        recyclerView.getAdapter().notifyDataSetChanged();

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // TODO Handle item click
                        // String province = itemList.get(position - 1);

                        RecycleAdapter.pos = position;
                        recyclerView.getAdapter().notifyDataSetChanged();


                        if(position == 0){
                            Intent myIntent = new Intent(getActivity().getApplicationContext(),LAQuiz.class);
                            myIntent.putExtra("Title", "Biology");
                            startActivity(myIntent);
                        }
                        else if(position == 1){
                            Intent myIntent = new Intent(getActivity().getApplicationContext(),LAQuiz.class);
                            myIntent.putExtra("Title", "Chemistry");
                            startActivity(myIntent);
                        }
                        else if(position == 2){
                            Intent myIntent = new Intent(getActivity().getApplicationContext(),LAQuiz.class);
                            myIntent.putExtra("Title", "Geology");
                            startActivity(myIntent);
                        }
                        else if(position == 3){
                            Intent myIntent = new Intent(getActivity().getApplicationContext(),LAQuiz.class);
                            myIntent.putExtra("Title", "Language Arts");
                            startActivity(myIntent);
                        }
                        else if(position == 4){
                            Intent myIntent = new Intent(getActivity().getApplicationContext(),LAQuiz.class);
                            myIntent.putExtra("Title", "Math");
                            startActivity(myIntent);
                        }
                        else if(position == 5){
                            Intent myIntent = new Intent(getActivity().getApplicationContext(),LAQuiz.class);
                            myIntent.putExtra("Title", "Physics");
                            startActivity(myIntent);
                        }
                        else if(position == 6){
                            Intent myIntent = new Intent(getActivity().getApplicationContext(),LAQuiz.class);
                            myIntent.putExtra("Title", "Social Studies");
                            startActivity(myIntent);
                        }

                    }
                })
        );


        return recyclerView;
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        RecycleAdapter recyclerAdapter = new RecycleAdapter(createItemList(), createItemPics());
        recyclerView.setAdapter(recyclerAdapter);
    }

    private List<String> createItemList() {
        itemList = new ArrayList<>();
        itemList.add("Biology");
        itemList.add("Chemistry");
        itemList.add("Geology");
        itemList.add("Language Arts");
        itemList.add("Math");
        itemList.add("Physics");
        itemList.add("Social Studies");
        return itemList;
    }

    private List<Integer> createItemPics(){
        itemPics = new ArrayList<>();
        itemPics.add(R.drawable.ic_biology);
        itemPics.add(R.drawable.ic_chemistry);
        itemPics.add(R.drawable.ic_geology);
        itemPics.add(R.drawable.ic_pages);
        itemPics.add(R.drawable.ic_people);
        itemPics.add(R.drawable.ic_physics);
        itemPics.add(R.drawable.ic_communities);
        return itemPics;
    }

}
