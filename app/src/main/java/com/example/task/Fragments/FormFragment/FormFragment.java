package com.example.task.Fragments.FormFragment;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.example.task.Data.POJO.User;
import com.example.task.R;
import com.example.task.databinding.FragmentFormBinding;
import com.google.android.material.textfield.TextInputEditText;

public class FormFragment extends Fragment {

    private FormVM mViewModel;
    private FragmentFormBinding mBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_form, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding = FragmentFormBinding.bind(view);
        mViewModel = ViewModelProviders.of(this).get(FormVM.class);

        mBinding.fgFormSaveBt.setOnClickListener(v -> {

            if (!isEmpty(mBinding.fgFormAgeEd,
                    mBinding.fgFormGenderEd,
                    mBinding.fgFormJobTitleEd,
                    mBinding.fgFormNameEd)) {

                User user = new User();
                user.setAge(mBinding.fgFormAgeEd.getText().toString());
                user.setGender(mBinding.fgFormGenderEd.getText().toString());
                user.setJobTitle(mBinding.fgFormJobTitleEd.getText().toString());
                user.setName(mBinding.fgFormNameEd.getText().toString());

                mViewModel.setUser(user);

                Navigation.findNavController(v).navigate(R.id.action_formFragment_to_listFragment);
            }

        });

    }

    private boolean isEmpty(TextInputEditText... editTexts) {
        boolean isEmpty = false;
        for (int i = 0; i < editTexts.length; i++) {
            String value = editTexts[i].getText().toString();
            if (TextUtils.isEmpty(value)) {
                editTexts[i].setError("please enter " + editTexts[i].getHint());
                isEmpty = true;
            }
        }
        return isEmpty;
    }

}
