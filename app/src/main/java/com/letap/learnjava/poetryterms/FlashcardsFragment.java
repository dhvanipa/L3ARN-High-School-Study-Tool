package com.letap.learnjava.poetryterms;

/**
 * Created by Dhvani on 07/12/2015.
 */


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Dhvani on 07/12/2015.
 */
public class FlashcardsFragment extends Fragment {

    List<String> itemList;
    List<Integer> itemPics;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final RecyclerView recyclerView = (RecyclerView) inflater.inflate(
                R.layout.fragment_flashcards, container, false);
        RecycleAdapter.pos = -1;
        setupRecyclerView(recyclerView);
        recyclerView.getAdapter().notifyDataSetChanged();


        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // TODO Handle item click




                        // String province = itemList.get(position);
                        //RecycleAdapter.back.setSelected(true);
                     // RecycleAdapter.setBackgroundColor(Color.BLUE);
                       RecycleAdapter.pos = position;
                       recyclerView.getAdapter().notifyDataSetChanged();




                        if(position == 0){
                            Intent myIntent = new Intent(getContext(),FlashcardDetail.class);
                            myIntent.putExtra("Category", "Instructions");
                            startActivity(myIntent);
                        }
                        else if(position == 1){
                            Intent myIntent = new Intent(getActivity().getApplicationContext(),FlashcardDetail.class);
                            myIntent.putExtra("Category", "Biology");
                            startActivity(myIntent);
                        }
                        else if(position == 2){
                            Intent myIntent = new Intent(getActivity().getApplicationContext(),FlashcardDetail.class);
                            myIntent.putExtra("Category", "Chemistry");
                            startActivity(myIntent);
                        }

                        else if(position == 3){
                            Intent myIntent = new Intent(getActivity().getApplicationContext(),FlashcardDetail.class);
                            myIntent.putExtra("Category", "Geology");
                            startActivity(myIntent);
                        }

                        else if(position == 4){
                            Intent myIntent = new Intent(getActivity().getApplicationContext(),FlashcardDetail.class);
                            myIntent.putExtra("Category", "Language Arts");
                            startActivity(myIntent);
                        }
                        else if(position == 5){
                            Intent myIntent = new Intent(getActivity().getApplicationContext(),FlashcardDetail.class);
                            myIntent.putExtra("Category", "Math");
                            startActivity(myIntent);
                        }
                        else if(position == 6){
                            Intent myIntent = new Intent(getActivity().getApplicationContext(),FlashcardDetail.class);
                            myIntent.putExtra("Category", "Physics");
                            startActivity(myIntent);
                        }
                        else if(position == 7){
                            Intent myIntent = new Intent(getActivity().getApplicationContext(),FlashcardDetail.class);
                            myIntent.putExtra("Category", "Social Studies");
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
        itemList.add("Instructions");
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
        itemPics.add(R.drawable.ic_instructions);
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

