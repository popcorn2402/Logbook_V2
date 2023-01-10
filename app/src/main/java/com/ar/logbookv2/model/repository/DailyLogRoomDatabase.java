package com.ar.logbookv2.model.repository;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.ar.logbookv2.model.dao.DailyLogDao;
import com.ar.logbookv2.model.entity.DailyLog;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {DailyLog.class}, version = 1, exportSchema = false)
public abstract class DailyLogRoomDatabase extends RoomDatabase{

    public abstract DailyLogDao dailyLogDao();

    private static volatile DailyLogRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static DailyLogRoomDatabase getDatabase(final Context context){
        if (INSTANCE == null) {
            synchronized (DailyLogRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    DailyLogRoomDatabase.class, "daily_log_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {
                DailyLogDao dailyLogDao = INSTANCE.dailyLogDao();
                dailyLogDao.deleteAll();

                DailyLog dailyLog1 = new DailyLog("DailyLog Test 1");
                dailyLogDao.insert(dailyLog1);

                DailyLog dailyLog2 = new DailyLog("DailyLog Test 2");
                dailyLogDao.insert(dailyLog2);
            });
        }
    };

}
