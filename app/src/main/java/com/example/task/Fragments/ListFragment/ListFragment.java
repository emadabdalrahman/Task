package com.example.task.Fragments.ListFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.task.Data.POJO.User;
import com.example.task.R;
import com.example.task.Utils.Adapter.BaseAdapter;
import com.example.task.Utils.Adapter.BaseViewHolder;
import com.example.task.databinding.ItemUserBinding;
import com.example.task.databinding.ListFragmentBinding;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment {

    private ListVM mViewModel;
    private ListFragmentBinding mBinding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding = ListFragmentBinding.bind(view);
        mViewModel = ViewModelProviders.of(this).get(ListVM.class);
        mViewModel.getUsers().observe(this, users -> {
            initRecyclerView(users);
        });
    }

    private void initRecyclerView(List<User> uses) {
        adapter.setDataSet((ArrayList<User>) uses);
        mBinding.fgListRecyclerView.setAdapter(adapter);
        mBinding.fgListRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mBinding.fgListRecyclerView.setHasFixedSize(true);
        mBinding.fgListRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
    }

    private BaseAdapter<ItemUserBinding, User> adapter = new BaseAdapter<ItemUserBinding, User>(R.layout.item_user) {
        @Override
        public Object getChangePayload(User oldObject, User newObject) {
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull BaseViewHolder<ItemUserBinding> VH, User user, int i) {
            VH.binding.itemJobTitle.setText(user.getJobTitle());
            VH.binding.userAge.setText(user.getAge());
            VH.binding.userGender.setText(user.getGender());
            VH.binding.userName.setText(user.getName());
        }
    };

}
