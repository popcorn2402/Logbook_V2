package com.ar.logbookv2.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.ar.logbookv2.entity.DailyLog;

import java.time.LocalDate;
import java.util.List;

@Dao
public interface DailyLogDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(DailyLog dailyLog);

    @Query("DELETE FROM daily_log_table WHERE date = :date")
    void deleteByDate(LocalDate date);

    @Query("DELETE FROM daily_log_table")
    void deleteAll();

    @Query("SELECT * FROM daily_log_table ORDER BY date DESC")
    LiveData<List<DailyLog>> getAlphabetizedDailyLog();

}
