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
import com.CDH.myapplication.ui.vistas.PlanillaFragment3ViewModel;

import java.util.ArrayList;
import java.util.List;

public class planillaFragment3 extends Fragment {
    

    private PlanillaFragment3ViewModel mViewModel;
    
    public static planillaFragment3 newInstance() {
        return new planillaFragment3();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.planilla_fragment3_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(PlanillaFragment3ViewModel.class);
        // TODO: Use the ViewModel
    }

    private ListView lista;
    List<String> items;
    ArrayAdapter ADP;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button button6=view.findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String codigo = getArguments().getString("codigo");
                Bundle bundle = new Bundle();
                bundle.putString("codigo", codigo);
                Navigation.findNavController(v).navigate(R.id.planillaFragment4,bundle);
            }
        });

        Button button5=view.findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String codigo = getArguments().getString("codigo");
                Bundle bundle = new Bundle();
                bundle.putString("codigo", codigo);
                Navigation.findNavController(v).navigate(R.id.planillaFragment2,bundle);
            }
        });
        final EditText txtnombre = findViewById(R.id.txtnombre);
        final EditText txtprecio = findViewById(R.id.txtprecio);
        final Button btnagregar= findViewById(R.id.btnagregar);
        lista = findViewById(R.id.lista);
        items = new ArrayList<>();
        ADP = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,items);
        lista.setAdapter(ADP);

        btnagregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                items.add(txtnombre.getText().toString());
                items.add(txtprecio.getText().toString());
                ADP.notifyDataSetChanged();
            }
        });
    }


}