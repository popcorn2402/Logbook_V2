package com.ar.logbookv2.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "daily_log_table")
public class DailyLog {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name="title")
    private String title;

    public DailyLog(@NonNull String title) {
        this.title = title;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }
}
