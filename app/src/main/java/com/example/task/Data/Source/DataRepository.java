package com.example.task.Data.Source;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.task.Data.POJO.User;
import com.example.task.Data.Source.Local.LocalDataSource;

import java.util.List;

public class DataRepository {

    private static DataRepository INSTANCE;
    private static final Object sLock = new Object();
    private LocalDataSource mLocal;

    public static DataRepository getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (sLock) {
                if (INSTANCE == null) {
                    INSTANCE = new DataRepository(context);
                }
            }
        }
        return INSTANCE;
    }

    public DataRepository(Context context) {
        mLocal = LocalDataSource.getInstance(context);
    }

    public void insertAll(User... users) {
        mLocal.insertAll(users);
    }

    public LiveData<List<User>> getAll() {
        return mLocal.getAll();
    }

}
