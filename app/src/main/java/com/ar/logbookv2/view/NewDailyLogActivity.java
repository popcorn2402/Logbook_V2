package com.ar.logbookv2.view;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.TypeConverters;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.ar.logbookv2.Converters;
import com.ar.logbookv2.R;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@TypeConverters({Converters.class})
public class NewDailyLogActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";

    private EditText mEditDateView;
    private EditText mEditMoodView;
    private EditText mEditEnergyView;
    private EditText mEditNotesView;

    private boolean format = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_daily_log);

        mEditDateView = findViewById(R.id.date_ed);
        mEditMoodView = findViewById(R.id.mood_ed);
        mEditEnergyView = findViewById(R.id.energy_ed);
        mEditNotesView = findViewById(R.id.notes_ed);

        LocalDate today = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            today = LocalDate.now();
        }

        assert today != null;
        mEditDateView.setText(today.toString());

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(view -> {
            Intent replyIntent = new Intent();

            if (TextUtils.isEmpty(mEditDateView.getText()) ||
                    TextUtils.isEmpty(mEditMoodView.getText()) ||
                    TextUtils.isEmpty(mEditEnergyView.getText()) ||
                    TextUtils.isEmpty(mEditNotesView.getText())
            ) {
                replyIntent.putExtra("Message", "Empty");
                setResult(RESULT_CANCELED, replyIntent);

                finish();
            } else {

                //LocalDate

                DateTimeFormatter formatter = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                }

                String local_date = mEditDateView.getText().toString();

                //convert String to LocalDate
                LocalDate date = null;

                try {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        date = LocalDate.parse(local_date, formatter);
                    }
                } catch (Exception e) {
                    replyIntent.putExtra("Message", "Format");
                    setResult(RESULT_CANCELED, replyIntent);

                    format = false;
                }

                if (format) {
                    int mood = Integer.parseInt(mEditMoodView.getText().toString());
                    int energy = Integer.parseInt(mEditEnergyView.getText().toString());
                    String notes = mEditNotesView.getText().toString();

                    //Intent

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        replyIntent.putExtra("Date", date);
                    }

                    replyIntent.putExtra("Mood", mood);
                    replyIntent.putExtra("Energy", energy);
                    replyIntent.putExtra("Notes", notes);

                    setResult(RESULT_OK, replyIntent);

                    finish();
                }
                finish();
            }
        });

    }
}