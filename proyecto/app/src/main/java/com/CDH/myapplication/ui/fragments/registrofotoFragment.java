package com.CDH.myapplication.ui.fragments;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.CDH.myapplication.R;
import com.CDH.myapplication.ui.vistas.RegistrofotoViewModel;

public class registrofotoFragment extends Fragment {

    private RegistrofotoViewModel mViewModel;

    public static registrofotoFragment newInstance() {
        return new registrofotoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.registrofoto_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(RegistrofotoViewModel.class);
        // TODO: Use the ViewModel
    }

}