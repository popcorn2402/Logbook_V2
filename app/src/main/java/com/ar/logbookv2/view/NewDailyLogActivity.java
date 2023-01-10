package com.ar.logbookv2.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.TypeConverters;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ar.logbookv2.Converters;
import com.ar.logbookv2.R;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;

@TypeConverters({Converters.class})
public class NewDailyLogActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";

    private EditText mEditTitleView;
    private EditText mEditDateView;
    private EditText mEditMoodView;
    private EditText mEditEnergyView;
    private EditText mEditNotesView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_daily_log);

        mEditTitleView = findViewById(R.id.title_ed);
        mEditDateView = findViewById(R.id.date_ed);
        mEditMoodView = findViewById(R.id.mood_ed);
        mEditEnergyView = findViewById(R.id.energy_ed);
        mEditNotesView = findViewById(R.id.notes_ed);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if ( TextUtils.isEmpty(mEditDateView.getText())) {
                setResult(RESULT_CANCELED, replyIntent);
            } else {
                String title = mEditTitleView.getText().toString();

                //LocalDate

                DateTimeFormatter formatter = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                }
                String local_date = mEditDateView.getText().toString();

                //convert String to LocalDate
                LocalDate date = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    date = LocalDate.parse(local_date, formatter);
                }

                int mood = Integer.parseInt(mEditMoodView.getText().toString());
                int energy = Integer.parseInt(mEditEnergyView.getText().toString());
                String notes = mEditNotesView.getText().toString();

                //Intent

                replyIntent.putExtra("Title", title);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    replyIntent.putExtra("Date", date);
                }

                replyIntent.putExtra("Mood", mood);
                replyIntent.putExtra("Energy", energy);
                replyIntent.putExtra("Notes", notes);

                setResult(RESULT_OK, replyIntent);
            }
            finish();
        });
    }
}