package com.ar.logbookv2.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ar.logbookv2.R;

public class DailyLogViewHolder extends RecyclerView.ViewHolder {

    private final TextView titleItemView;

    private DailyLogViewHolder(@NonNull View itemView) {
        super(itemView);

        titleItemView = itemView.findViewById(R.id.title_tv);
    }

    public void bind (String title){
        titleItemView.setText(title);
    }

    static DailyLogViewHolder create(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new DailyLogViewHolder(view);
    }
}
