package com.ar.logbookv2.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.ar.logbookv2.model.entity.DailyLog;

import java.util.List;

@Dao
public interface DailyLogDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(DailyLog dailyLog);

    @Query("DELETE FROM daily_log_table WHERE title = :title")
    void deleteByTitle(String title);

    @Query("DELETE FROM daily_log_table")
    void deleteAll();

    @Query("SELECT * FROM daily_log_table ORDER BY title ASC")
    LiveData<List<DailyLog>> getAlphabetizedDailyLog();

}
