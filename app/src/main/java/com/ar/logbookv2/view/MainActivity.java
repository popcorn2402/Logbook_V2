package com.ar.logbookv2.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.ar.logbookv2.R;
import com.ar.logbookv2.entity.DailyLog;
import com.ar.logbookv2.view.essaiviewpager.DailyLogViewPager;
import com.ar.logbookv2.view.recyclerview.DailyLogListAdapter;
import com.ar.logbookv2.view.recyclerview.OnRecyclerViewItemClicked;
import com.ar.logbookv2.viewmodel.DailyLogViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalDate;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements OnRecyclerViewItemClicked {

    private DailyLogViewModel mDailyLogViewModel;
    public static final int NEW_DAILY_LOG_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final DailyLogListAdapter adapter = new DailyLogListAdapter(new DailyLogListAdapter.DailyLogDiff(), this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        mDailyLogViewModel = new ViewModelProvider(this).get(DailyLogViewModel.class);
        mDailyLogViewModel.getAllDailyLog().observe(this, dailyLogs -> {
            adapter.submitList(dailyLogs);
        });

        try {
            FloatingActionButton fab = findViewById(R.id.fab);
            fab.setOnClickListener( view -> {
                Intent intent = new Intent(MainActivity.this, NewDailyLogActivity.class);
                startActivityForResult(intent, NEW_DAILY_LOG_ACTIVITY_REQUEST_CODE);
            });
        }
        catch (Exception e){
            System.out.println("Error launching NewDailyLogActivity");
        }

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null) {
            if (requestCode == NEW_DAILY_LOG_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {

                LocalDate date = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    date = (LocalDate) data.getSerializableExtra("Date");
                }

                int mood = data.getIntExtra("Mood", 0);
                int energy = data.getIntExtra("Energy", 0);
                String notes = data.getStringExtra("Notes");

                try {
                    DailyLog dailyLog = new DailyLog(date, mood, energy, notes);
                    mDailyLogViewModel.insert(dailyLog);
                }
                catch (Exception e){
                    Toast.makeText(this, R.string.date_already_saved, Toast.LENGTH_SHORT).show();
                }

            }
            else if (resultCode == RESULT_CANCELED && Objects.equals(data.getStringExtra("Message"), "Empty")){
                Toast.makeText(
                        getApplicationContext(),
                        R.string.empty_not_saved,
                        Toast.LENGTH_LONG).show();
                }
            else if (resultCode == RESULT_CANCELED && Objects.equals(data.getStringExtra("Message"), "Format")){
                Toast.makeText(
                        getApplicationContext(),
                        R.string.wrong_date_format,
                        Toast.LENGTH_LONG).show();
            }
        }
    }

    public void onShortClick(int position, LocalDate date, int mood, int energy, String notes){
        Intent intent = new Intent(MainActivity.this, DisplayInfo.class);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            intent.putExtra("Date", date);
        }
        intent.putExtra("Mood", mood);
        intent.putExtra("Energy", energy);
        intent.putExtra("Notes", notes);
        startActivity(intent);
    }

    @Override
    public void onLongClick(int position, LocalDate date) {
        mDailyLogViewModel.deleteByDate(date);
    }
}