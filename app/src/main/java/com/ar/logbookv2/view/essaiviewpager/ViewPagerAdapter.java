package com.ar.logbookv2.view.essaiviewpager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.ar.logbookv2.R;
import com.ar.logbookv2.entity.DailyLog;
import com.ar.logbookv2.viewmodel.DailyLogViewModel;

import java.time.LocalDate;

public class ViewPagerAdapter extends ListAdapter<DailyLog, ViewPagerAdapter.PagerViewHolder> {

    private DailyLogViewModel mDailyLogViewModel;

    public ViewPagerAdapter(@NonNull DiffUtil.ItemCallback<DailyLog> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public PagerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_holder, parent, false);

        return new PagerViewHolder(view);
    }


    public void onBindViewHolder(@NonNull PagerViewHolder holder, final int position) {
        DailyLog current = getItem(position);
        holder.bind(current.getDate(), current.getMood(), current.getEnergy());
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

    public static class PagerViewHolder extends RecyclerView.ViewHolder {

        //public final TextView dateItemView;
        private final TextView moodItemView;
        //private final TextView energyItemView;

        private PagerViewHolder(@NonNull View itemView) {
            super(itemView);

            moodItemView = itemView.findViewById(R.id.content);
        }

        public void bind (LocalDate date, int mood, int energy){
            moodItemView.setText(Integer.toString(mood));
        }
    }
}
