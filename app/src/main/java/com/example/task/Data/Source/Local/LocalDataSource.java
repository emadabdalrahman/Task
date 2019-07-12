package com.example.task.Data.Source.Local;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.task.Data.POJO.User;
import com.example.task.Utils.AppExecutors;

import java.util.List;

public class LocalDataSource {

    private static LocalDataSource INSTANCE;
    private static final Object sLock = new Object();
    private UserDao mUserDao;
    private AppExecutors mAppExecutors;

    public static LocalDataSource getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (sLock) {
                if (INSTANCE == null) {
                    INSTANCE = new LocalDataSource(context);
                }
            }
        }
        return INSTANCE;
    }

    private LocalDataSource(Context context) {
        mUserDao = AppDatabase.getInstance(context).userDao();
        mAppExecutors = new AppExecutors();
    }

    public void insertAll(User... users) {
        mAppExecutors.getIOThread().execute(() -> {
            mUserDao.insertAll(users);
        });
    }

    public LiveData<List<User>> getAll() {
        return mUserDao.getAll();
    }

}
