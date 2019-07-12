package com.example.task.Utils.Adapter;


import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import java.util.ArrayList;

public class BaseDiffUtil<T> extends DiffUtil.Callback {

    private final ArrayList<T> oldD;
    private final ArrayList<T> newD;
    private final BaseDiffUtilListener mListener;

    interface BaseDiffUtilListener<T> {
        Object getChangePayload(T oldObject, T newObject);
    }

    public BaseDiffUtil(ArrayList<T> oldD, ArrayList<T> newD, BaseDiffUtilListener listener) {
        this.oldD = oldD;
        this.newD = newD;
        this.mListener = listener;
    }

    @Override
    public int getOldListSize() {
        return oldD != null ? oldD.size() : 0;
    }

    @Override
    public int getNewListSize() {
        return newD != null ? newD.size() : 0;
    }

    @Override
    public boolean areItemsTheSame(int i, int i1) {
        return oldD.get(i) == newD.get(i1);
    }

    @Override
    public boolean areContentsTheSame(int i, int i1) {
        return oldD.get(i).equals(newD.get(i1));
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return mListener != null ? mListener.getChangePayload(oldD.get(oldItemPosition), newD.get(newItemPosition)) : null;
    }

}
