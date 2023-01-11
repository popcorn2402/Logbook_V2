package com.ar.logbookv2.view.recyclerview;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.ar.logbookv2.entity.DailyLog;
import com.ar.logbookv2.viewmodel.DailyLogViewModel;

public class DailyLogListAdapter extends ListAdapter<DailyLog, DailyLogViewHolder> {
    private final OnRecyclerViewItemClicked listener;
    private DailyLogViewModel mDailyLogViewModel;

    public DailyLogListAdapter(@NonNull DiffUtil.ItemCallback<DailyLog> diffCallback, OnRecyclerViewItemClicked onRecyclerViewItemClicked) {
        super(diffCallback);

        this.listener = onRecyclerViewItemClicked;
    }

    @NonNull
    @Override
    public DailyLogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return DailyLogViewHolder.create(parent, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull DailyLogViewHolder holder, final int position) {
        DailyLog current = getItem(position);
        holder.bind(current.getDate(), current.getMood(), current.getEnergy());

        int pos = position;

        holder.dateItemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if(listener != null)
                    if(pos != RecyclerView.NO_POSITION) {
                        listener.onLongClick(pos, current.getDate());
                        return true;
                    }
                return false;
            }
        });

    }

    public static class DailyLogDiff extends DiffUtil.ItemCallback<DailyLog>{

        @Override
        public boolean areItemsTheSame(@NonNull DailyLog oldItem, @NonNull DailyLog newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull DailyLog oldItem, @NonNull DailyLog newItem) {
            return oldItem.getDate().equals(newItem.getDate());
        }
    }
}
