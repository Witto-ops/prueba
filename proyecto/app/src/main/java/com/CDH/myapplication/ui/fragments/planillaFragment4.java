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
import android.widget.Toast;

import com.CDH.myapplication.R;
import com.CDH.myapplication.ui.vistas.PlanillaFragment4ViewModel;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class planillaFragment4 extends Fragment {

    private PlanillaFragment4ViewModel mViewModel;

    public static planillaFragment4 newInstance() {
        return new planillaFragment4();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.planilla_fragment4_fragment, container, false);
        Bundle bundle = getArguments();
        if(bundle!=null ) {
            if(getArguments().getSerializable("objetos2")!=null){
                lista = vista.findViewById(R.id.lista);
                items = new ArrayList<>();
                ADP = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1, (ArrayList) getArguments().getSerializable("objetos2"));
                lista.setAdapter(ADP);
                ADP.notifyDataSetChanged();
            }
        }
        if(bundle!=null ){
            if(bundle.getString("mod").equals("1")){
                String codigo = getArguments().getString("codigo");
                buscaFactura("http://192.168.56.1/wappservice/buscar_ficha.php?codig="+codigo+"");
            }
        }
        return vista;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(PlanillaFragment4ViewModel.class);
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
        Bundle bundle = getArguments();
        if(bundle!=null ) {

            if(getArguments().getSerializable("objetos2")!=null && bundle.getString("mod").equals("2")){
                items=(ArrayList) getArguments().getSerializable("objetos2");
                lista = view.findViewById(R.id.lista);
                ADP = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,items);
                lista.setAdapter(ADP);
                ADP.notifyDataSetChanged();

            }else{
                lista = view.findViewById(R.id.lista);
                items = new ArrayList<>();
                ADP = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,items);
                lista.setAdapter(ADP);
            }
        }else{
            lista = view.findViewById(R.id.lista);
            items = new ArrayList<>();
            ADP = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,items);
            lista.setAdapter(ADP);
        }

        btnagregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                items.add(txtnombre.getText().toString()+" "+txtprecio.getText().toString());
               // items.add(txtprecio.getText().toString());
                ADP.notifyDataSetChanged();
            }
        });

        Button button6=view.findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String codigo = getArguments().getString("codigo");
                Bundle bundle = getArguments();
                bundle.putString("codigo", codigo);
                bundle.putSerializable("objetos2", (Serializable) items);
                if(bundle.getString("mod").equals("1")){
                    // ejecutarServicio("http://192.168.56.1/wappservice/modifica2.php",codigo);
                    Navigation.findNavController(v).navigate(R.id.planillaFragment5,bundle);
                }else{
                    Navigation.findNavController(v).navigate(R.id.planillaFragment5,bundle);
                }
            }
        });

        Button button5=view.findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String codigo = getArguments().getString("codigo");
                Bundle bundle = getArguments();
                bundle.putString("codigo", codigo);
                bundle.putSerializable("objetos2", (Serializable) items);
                if(bundle.getString("mod").equals("1")){
                    // ejecutarServicio("http://192.168.56.1/wappservice/modifica2.php",codigo);
                    Navigation.findNavController(v).navigate(R.id.planillaFragment3,bundle);
                }else{
                    Navigation.findNavController(v).navigate(R.id.planillaFragment3,bundle);
                }
            }
        });
    }

    private void buscaFactura(String URL){
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        items.add(jsonObject.getString("nombre")+" "+jsonObject.getString("costo"));
                        //  items.add(txtprecio.getText().toString());
                        ADP.notifyDataSetChanged();

                    } catch (JSONException e) {
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Error de conexion ", Toast.LENGTH_SHORT).show();

            }
        }
        );
        RequestQueue requestQueue= Volley.newRequestQueue(this.getContext());
        requestQueue.add(jsonArrayRequest);
    }
}