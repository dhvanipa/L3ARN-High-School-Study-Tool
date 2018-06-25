package com.letap.learnjava.poetryterms;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Dhvani on 10/12/2015.
 */
public class RecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<String> mItemList;
    private List<Integer> mItemPic;
   static RecyclerItemViewHolder holder;
    static int pos = -1;

    public RecycleAdapter(List<String> itemList, List<Integer> itemPic) {
        mItemList = itemList;
        mItemPic = itemPic;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);

        return RecyclerItemViewHolder.newInstance(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
       holder = (RecyclerItemViewHolder) viewHolder;

        String itemText = mItemList.get(position);
        holder.setItemText(itemText);
        int itemPic = mItemPic.get(position);
        holder.setItemImage(itemPic);
        if(pos == position){
            ((CardView)RecycleAdapter.holder.itemView).setCardBackgroundColor(Color.parseColor("#28A828"));
        }
        else{
            ((CardView)RecycleAdapter.holder.itemView).setCardBackgroundColor(Color.TRANSPARENT);
        }

    }

    @Override
    public int getItemCount() {
        return mItemList == null ? 0 : mItemList.size();
    }







}
