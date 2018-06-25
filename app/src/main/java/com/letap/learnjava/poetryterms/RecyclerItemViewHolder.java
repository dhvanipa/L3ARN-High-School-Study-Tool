package com.letap.learnjava.poetryterms;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Dhvani on 10/12/2015.
 */

public class RecyclerItemViewHolder extends RecyclerView.ViewHolder {
    private final TextView mItemTextView;
    private final ImageView mItemImageView;

    public RecyclerItemViewHolder(final View parent, TextView itemTextView, ImageView itemImageView) {
        super(parent);
        mItemTextView = itemTextView;
        mItemImageView = itemImageView;
    }

    public static RecyclerItemViewHolder newInstance(View parent) {
        TextView itemTextView = (TextView) parent.findViewById(R.id.itemTextView);
        ImageView itemImageView = (ImageView)parent.findViewById(R.id.list_image);
        return new RecyclerItemViewHolder(parent, itemTextView, itemImageView);
    }

    public void setItemText(CharSequence text) {
        mItemTextView.setText(text);
    }

    public void setItemImage(int drawable){
        mItemImageView.setBackgroundResource(drawable);
    }

}
