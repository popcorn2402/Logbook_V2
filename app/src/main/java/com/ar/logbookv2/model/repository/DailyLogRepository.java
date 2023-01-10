package com.ar.logbookv2.model.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.ar.logbookv2.model.dao.DailyLogDao;
import com.ar.logbookv2.entity.DailyLog;

import java.time.LocalDate;
import java.util.List;

public class DailyLogRepository {

    private final DailyLogDao mDailyLogDao;
    private LiveData<List<DailyLog>> mAllDailyLog;


    public DailyLogRepository(Application application) {
        DailyLogRoomDatabase db = DailyLogRoomDatabase.getDatabase(application);
        mDailyLogDao = db.dailyLogDao();
        mAllDailyLog = mDailyLogDao.getAlphabetizedDailyLog();
    }

    public LiveData<List<DailyLog>> getAllDailyLog(){
        return mAllDailyLog;
    }

    public void insert(DailyLog dailyLog){
        DailyLogRoomDatabase.databaseWriteExecutor.execute(() -> {
            mDailyLogDao.insert(dailyLog);
        });
    }

    public void deleteByDate(LocalDate date){
        DailyLogRoomDatabase.databaseWriteExecutor.execute(() -> {
            mDailyLogDao.deleteByDate(date);
        });
    }
}
