package com.example.task.Utils.Adapter;


import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

public class BaseViewHolder<H extends ViewDataBinding> extends RecyclerView.ViewHolder {

    private final BaseItemListener mListener;
    public final H binding;


    public BaseViewHolder(@NonNull H binding, @NonNull BaseItemListener listener) {
        super(binding.getRoot());
        this.binding = binding;
        this.mListener = listener;
        binding.getRoot().setOnClickListener(mOnClickListener);
    }

    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mListener != null) mListener.onClick(v, getLayoutPosition());
        }
    };

}
