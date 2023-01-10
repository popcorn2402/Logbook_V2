package com.ar.logbookv2.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.ar.logbookv2.R;

public class NewDailyLogActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";

    private EditText mEditDailyLogView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_daily_log);
        mEditDailyLogView = findViewById(R.id.edit_daily_log);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(mEditDailyLogView.getText())) {
                setResult(RESULT_CANCELED, replyIntent);
            } else {
                String dailyLog = mEditDailyLogView.getText().toString();
                replyIntent.putExtra(EXTRA_REPLY, dailyLog);
                setResult(RESULT_OK, replyIntent);
            }
            finish();
        });
    }
}