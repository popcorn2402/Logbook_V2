package com.ar.logbookv2.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ar.logbookv2.R;

import java.time.LocalDate;

public class DailyLogViewHolder extends RecyclerView.ViewHolder {

    private final TextView titleItemView;
    private final TextView dateItemView;

    private DailyLogViewHolder(@NonNull View itemView) {
        super(itemView);

        titleItemView = itemView.findViewById(R.id.title_tv);
        dateItemView = itemView.findViewById(R.id.date_tv);
    }

    public void bind (String title, LocalDate date){
        titleItemView.setText(title);
        dateItemView.setText(date.toString());
    }

    static DailyLogViewHolder create(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new DailyLogViewHolder(view);
    }
}
