package com.ar.logbookv2.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ar.logbookv2.model.entity.DailyLog;
import com.ar.logbookv2.model.repository.DailyLogRepository;

import java.util.List;

public class DailyLogViewModel extends AndroidViewModel {

    private DailyLogRepository mRepository;
    private LiveData<List<DailyLog>> mAllDailyLog;

    public DailyLogViewModel(@NonNull Application application) {
        super(application);

        mRepository = new DailyLogRepository(application);
        mAllDailyLog = mRepository.getAllDailyLog();
    }

    LiveData<List<DailyLog>> getAllDailyLog(){
        return mAllDailyLog;
    }

    public void insert(DailyLog dailyLog){
        mRepository.insert(dailyLog);
    }

    public void deleteByTitle (String title){
        mRepository.deleteByTitle(title);
    }
}
