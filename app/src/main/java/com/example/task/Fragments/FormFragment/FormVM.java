package com.example.task.Fragments.FormFragment;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.task.Data.POJO.User;
import com.example.task.Data.Source.DataRepository;

public class FormVM extends AndroidViewModel {

    private DataRepository mDataRepository;

    public FormVM(@NonNull Application application) {
        super(application);
        mDataRepository = DataRepository.getInstance(application.getApplicationContext());
    }

    public void setUser(User user) {
        mDataRepository.insertAll(user);
    }

}