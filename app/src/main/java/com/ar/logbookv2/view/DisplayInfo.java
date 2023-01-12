package com.ar.logbookv2.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.ar.logbookv2.R;

public class DisplayInfo extends AppCompatActivity {
    private TextView date;
    private TextView mood;
    private TextView energy;
    private TextView notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_info);

        date = findViewById(R.id.display_daily_log_date);
        mood = findViewById(R.id.display_daily_log_mood);
        energy = findViewById(R.id.display_daily_log_energy);
        notes = findViewById(R.id.display_daily_log_notes);

        Intent intent = getIntent();

        date.setText(intent.getSerializableExtra("Date").toString());
        mood.setText(Integer.toString(intent.getIntExtra("Mood", 0)));
        energy.setText(Integer.toString(intent.getIntExtra("Energy", 0)));
        notes.setText(intent.getStringExtra("Notes"));

    }
}