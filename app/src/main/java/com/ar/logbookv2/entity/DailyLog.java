package com.ar.logbookv2.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDate;

@Entity(tableName = "daily_log_table")
public class DailyLog {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "date")
    private LocalDate date;

    @ColumnInfo(name = "mood")
    private int mood;

    @ColumnInfo(name = "energy")
    private int energy;

    @ColumnInfo(name = "notes")
    private String notes;

    public DailyLog(@NonNull LocalDate date, int mood, int energy, String notes) {
        this.date = date;
        this.mood = mood;
        this.energy = energy;
        this.notes = notes;
    }

    @NonNull
    public LocalDate getDate() {
        return date;
    }

    public void setDate(@NonNull LocalDate date) {
        this.date = date;
    }

    public int getMood() {
        return mood;
    }

    public void setMood(int mood) {
        this.mood = mood;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
