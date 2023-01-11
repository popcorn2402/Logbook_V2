/*
package com.ar.logbookv2.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.ar.logbookv2.R;
import com.ar.logbookv2.entity.DailyLog;

import java.util.List;

public class ViewPager2Adapter extends RecyclerView.Adapter<ViewPager2Adapter.ViewHolder> {

    private LiveData<List<DailyLog>> list;
    private Context ctx;

    // Constructor of our ViewPager2Adapter class
    ViewPager2Adapter(Context ctx) {
        this.ctx = ctx;
    }

    // This method returns our layout
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.images_holder, parent, false);
        return new ViewHolder(view);
    }

    // This method binds the screen with the view
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // This will set the images in imageview
        holder.images.setImageResource(images[position]);
    }

    // This Method returns the size of the Array
    @Override
    public int getItemCount() {
        return images.length;
    }

    // The ViewHolder class holds the view
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView images;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            images = itemView.findViewById(R.id.images);
        }
    }
}
*/
