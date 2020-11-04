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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.CDH.myapplication.R;
import com.CDH.myapplication.ui.vistas.PlanillaFragment5ViewModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
    private ListView lista;
    List<String> items;
    ArrayAdapter ADP;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final EditText txtnombre = view.findViewById(R.id.txtnombre);
        final EditText txtprecio = view.findViewById(R.id.txtprecio);
        final Button btnagregar= view.findViewById(R.id.btnagregar);


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

        lista = view.findViewById(R.id.lista);
        items = new ArrayList<>();
        ADP = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,items);
        lista.setAdapter(ADP);

        btnagregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                items.add(txtnombre.getText().toString()+" "+txtprecio.getText().toString());
                //items.add(txtprecio.getText().toString());
                ADP.notifyDataSetChanged();
            }
        });

        Button button6=view.findViewById(R.id.btnS2);
        button6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String codigo = getArguments().getString("codigo");
                Bundle bundle = getArguments();
                bundle.putString("codigo", codigo);
                bundle.putSerializable("objetos3", (Serializable) items);

                Navigation.findNavController(v).navigate(R.id.resumenFragment,bundle);
            }
        });
    }
}