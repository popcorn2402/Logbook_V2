package com.ar.logbookv2.view.recyclerview;

import android.content.Context;
import android.widget.Toast;

public interface OnRecyclerViewItemClicked
{
    /**
     * Called when any item with in recyclerview or any item with in item
     * clicked
     *
     * @param position
     *            The position of the item
     * @param id
     *            The id of the view which is clicked with in the item or
     *            -1 if the item itself clicked
     */
    public static void onRecyclerViewItemClicked(int position, int id, Context context){

        if(id==1){
            Toast.makeText(context, "short click", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(context, "long click", Toast.LENGTH_LONG).show();
        }

    }
}