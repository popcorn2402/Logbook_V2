package com.ar.logbookv2.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.ar.logbookv2.R;
import com.ar.logbookv2.entity.DailyLog;
import com.ar.logbookv2.viewmodel.DailyLogViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MainActivity extends AppCompatActivity {

    private DailyLogViewModel mDailyLogViewModel;
    public static final int NEW_DAILY_LOG_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final DailyLogListAdapter adapter = new DailyLogListAdapter(new DailyLogListAdapter.DailyLogDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

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

        if (requestCode == NEW_DAILY_LOG_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {

            LocalDate date = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                date = (LocalDate) data.getSerializableExtra("Date");
            }
            String title = data.getStringExtra("Title");
            int mood = data.getIntExtra("Mood", 0);
            int energy = data.getIntExtra("Energy", 0);
            String notes = data.getStringExtra("Notes");

            DailyLog dailyLog = new DailyLog(date, title, mood, energy, notes);
            mDailyLogViewModel.insert(dailyLog);

        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }
}