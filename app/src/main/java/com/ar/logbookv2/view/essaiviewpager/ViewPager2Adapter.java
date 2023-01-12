package com.ar.logbookv2.view.essaiviewpager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.ar.logbookv2.R;
import com.ar.logbookv2.entity.DailyLog;
import com.ar.logbookv2.viewmodel.DailyLogViewModel;

import java.util.List;

public class ViewPager2Adapter extends RecyclerView.Adapter<ViewPager2Adapter.ViewHolder> {

    private LiveData<List<DailyLog>> list;
    private final Context ctx;
    private int moodItem;

    private DailyLogViewModel model;

    public ViewPager2Adapter(Context ctx, int mood) {
        //super(diffCallback);
        this.ctx = ctx;
        this.moodItem = mood;
    }

    // This method returns our layout
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.content_holder, parent, false);
        return new ViewHolder(view);
    }

    // This method binds the screen with the view
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // This will set the images in imageview
        //DailyLog current;
        holder.bind(moodItem);

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView moodItemView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            moodItemView = itemView.findViewById(R.id.content);
        }

        public void bind (int mood){
            moodItemView.setText(Integer.toString(mood));
        }
    }
}
