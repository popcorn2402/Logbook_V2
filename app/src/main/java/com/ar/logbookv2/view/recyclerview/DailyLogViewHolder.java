package com.ar.logbookv2.view.recyclerview;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ar.logbookv2.R;

import java.time.LocalDate;

public class DailyLogViewHolder extends RecyclerView.ViewHolder {

    public final TextView dateItemView;
    private final TextView moodItemView;
    private final TextView energyItemView;
    public LinearLayout parentView;

    private DailyLogViewHolder(@NonNull View itemView) {
        super(itemView);

        dateItemView = itemView.findViewById(R.id.date_tv);
        moodItemView = itemView.findViewById(R.id.mood_tv);
        energyItemView = itemView.findViewById(R.id.energy_tv);
        parentView = itemView.findViewById(R.id.clickable_layout);
    }

    public void bind (LocalDate date, int mood, int energy){
        dateItemView.setText(date.toString());
        moodItemView.setText(Integer.toString(mood));
        energyItemView.setText(Integer.toString(energy));
    }

    public static DailyLogViewHolder create(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);

        return new DailyLogViewHolder(view);
    }
}
