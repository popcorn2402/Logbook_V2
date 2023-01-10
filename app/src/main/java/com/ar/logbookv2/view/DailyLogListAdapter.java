package com.ar.logbookv2.view;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.ar.logbookv2.entity.DailyLog;

public class DailyLogListAdapter extends ListAdapter<DailyLog, DailyLogViewHolder> {

    protected DailyLogListAdapter(@NonNull DiffUtil.ItemCallback<DailyLog> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public DailyLogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return DailyLogViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull DailyLogViewHolder holder, int position) {
        DailyLog current = getItem(position);
        holder.bind(current.getDate(), current.getMood(), current.getEnergy());
    }

    static class DailyLogDiff extends DiffUtil.ItemCallback<DailyLog>{

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
