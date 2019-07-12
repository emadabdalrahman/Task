package com.example.task.Fragments.ListFragment;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.task.Data.POJO.User;
import com.example.task.Data.Source.DataRepository;

import java.util.List;

public class ListVM extends AndroidViewModel {

    private LiveData<List<User>> users;
    private DataRepository mDataRepository;

    public ListVM(@NonNull Application application) {
        super(application);
        mDataRepository = DataRepository.getInstance(application.getApplicationContext());
    }

    public LiveData<List<User>> getUsers() {
        if (users == null) {
            loadUsers();
        }
        return users;
    }

    private void loadUsers() {
        users = mDataRepository.getAll();
    }

}
