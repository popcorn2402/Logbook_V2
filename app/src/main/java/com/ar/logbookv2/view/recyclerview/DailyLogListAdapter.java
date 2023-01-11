package com.ar.logbookv2.view.recyclerview;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.ar.logbookv2.entity.DailyLog;

public class DailyLogListAdapter extends ListAdapter<DailyLog, DailyLogViewHolder> {

    OnRecyclerViewItemClicked listener;

    public DailyLogListAdapter(@NonNull DiffUtil.ItemCallback<DailyLog> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public DailyLogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return DailyLogViewHolder.create(parent);
    }

    /**
     * Custom created method for Setting the item click listener for the items and items with in items
     * @param listener OnRecyclerViewItemClickListener
     */
    public void setOnItemClickListener(OnRecyclerViewItemClicked listener)
    {
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull DailyLogViewHolder holder, final int position) {
        DailyLog current = getItem(position);
        holder.bind(current.getDate(), current.getMood(), current.getEnergy());

        int pos = position;

        holder.dateItemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                OnRecyclerViewItemClicked.onRecyclerViewItemClicked(pos, 1, v.getContext());
            }
        });

        holder.dateItemView.setOnLongClickListener(new View.OnLongClickListener()
        {
            @Override
            public boolean onLongClick(View v)
            {
                OnRecyclerViewItemClicked.onRecyclerViewItemClicked(pos, 2, v.getContext());
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
