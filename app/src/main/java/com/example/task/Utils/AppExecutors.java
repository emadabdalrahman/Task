package com.example.task.Utils;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AppExecutors {

    private ExecutorService mIOThread;
    private Handler mMainThread;

    public AppExecutors() {
        mIOThread = Executors.newSingleThreadExecutor();
        mMainThread = new Handler(Looper.getMainLooper());
    }

    public ExecutorService getIOThread() {
        return mIOThread;
    }

    public Handler getMainThread() {
        return mMainThread;
    }


}
