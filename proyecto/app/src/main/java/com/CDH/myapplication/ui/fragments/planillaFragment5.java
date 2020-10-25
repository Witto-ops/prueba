package com.CDH.myapplication.ui.fragments;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.CDH.myapplication.R;
import com.CDH.myapplication.ui.vistas.PlanillaFragment5ViewModel;

public class planillaFragment5 extends Fragment {

    private PlanillaFragment5ViewModel mViewModel;

    public static planillaFragment5 newInstance() {
        return new planillaFragment5();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.planilla_fragment5_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(PlanillaFragment5ViewModel.class);
        // TODO: Use the ViewModel
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button button6=view.findViewById(R.id.btnS2);
        button6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String codigo = getArguments().getString("codigo");
                Bundle bundle = new Bundle();
                bundle.putString("codigo", codigo);
                Navigation.findNavController(v).navigate(R.id.planillaFragment7,bundle);
            }
        });

        Button button5=view.findViewById(R.id.btnA2);
        button5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String codigo = getArguments().getString("codigo");
                Bundle bundle = new Bundle();
                bundle.putString("codigo", codigo);
                Navigation.findNavController(v).navigate(R.id.planillaFragment4,bundle);
            }
        });
    }
}