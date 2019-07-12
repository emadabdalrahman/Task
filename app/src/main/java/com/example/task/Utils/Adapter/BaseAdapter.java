package com.example.task.Utils.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public abstract class BaseAdapter<H extends ViewDataBinding, T>
        extends RecyclerView.Adapter<BaseViewHolder<H>>
        implements BaseDiffUtil.BaseDiffUtilListener<T> {

    private final int mLayoutId;
    private ArrayList<T> mDataSet;

    private BaseItemListener mBaseItemListener = new BaseItemListener() {
        @Override
        public void onClick(View v, int position) {
            onItemClicked(v, mDataSet.get(position), position);
            onItemClicked(v, position);
        }
    };

    public BaseAdapter(int layoutId) {
        this.mLayoutId = layoutId;
    }

    @NonNull
    @Override
    public BaseViewHolder<H> onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ViewDataBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(viewGroup.getContext()),
                mLayoutId,
                viewGroup,
                false);
        return new BaseViewHolder<>((H) binding, mBaseItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder<H> VH, int i) {
        onBindViewHolder(VH, mDataSet.get(i), i);
        onBindViewHolder(VH, mDataSet.get(i));
    }

    public void onBindViewHolder(@NonNull BaseViewHolder<H> VH, T t, int i) {
    }

    public void onBindViewHolder(@NonNull BaseViewHolder<H> VH, T t) {
    }

    public void onItemClicked(View v, T t, int position) {
    }

    public void onItemClicked(View v, int position) {
    }

    @Override
    public int getItemCount() {
        return mDataSet != null ? mDataSet.size() : 0;
    }

    public ArrayList<T> getDataSet() {
        return mDataSet;
    }

    public void setDataSet(ArrayList<T> mDataSet) {
        this.mDataSet = mDataSet;
    }

    public T getData(int i) {
        return mDataSet.get(i);
    }

    public void updateList(ArrayList<T> newList) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new BaseDiffUtil<T>(mDataSet, newList, this));
        diffResult.dispatchUpdatesTo(this);
        mDataSet.clear();
        mDataSet.addAll(newList);
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        if (layoutManager != null) layoutManager.setRecycleChildrenOnDetach(true);
    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        mBaseItemListener = null;
    }
}
