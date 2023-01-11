package com.ar.logbookv2.view.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ar.logbookv2.R;

import java.time.LocalDate;

public class DailyLogViewHolder extends RecyclerView.ViewHolder {

    public final TextView dateItemView;
    private final TextView moodItemView;
    private final TextView energyItemView;
    public LinearLayout parentView;

    private DailyLogViewHolder(@NonNull View itemView, OnRecyclerViewItemClicked listener) {
        super(itemView);

        dateItemView = itemView.findViewById(R.id.date_tv);
        moodItemView = itemView.findViewById(R.id.mood_tv);
        energyItemView = itemView.findViewById(R.id.energy_tv);
        parentView = itemView.findViewById(R.id.clickable_layout);

        dateItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener != null){
                    int pos = getAdapterPosition();

                    if(pos != RecyclerView.NO_POSITION){
                        listener.onShortClick(pos);
                    }
                }
            }
        });
    }

    public void bind (LocalDate date, int mood, int energy){
        dateItemView.setText(date.toString());
        moodItemView.setText(Integer.toString(mood));
        energyItemView.setText(Integer.toString(energy));
    }

    public static DailyLogViewHolder create(ViewGroup parent, OnRecyclerViewItemClicked onRecyclerViewItemClicked){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);

        return new DailyLogViewHolder(view, onRecyclerViewItemClicked);
    }
}
