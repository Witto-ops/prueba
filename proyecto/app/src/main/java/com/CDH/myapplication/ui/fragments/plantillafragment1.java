package com.CDH.myapplication.ui.fragments;

import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.CDH.myapplication.R;
import com.CDH.myapplication.ui.vistas.Plantillafragment1ViewModel;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class plantillafragment1 extends Fragment {

    private Plantillafragment1ViewModel mViewModel;
    EditText codigoTXT;
    public static plantillafragment1 newInstance() {
        return new plantillafragment1();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View vista= inflater.inflate(R.layout.plantillafragment1_fragment, container, false);
        codigoTXT = (EditText) vista.findViewById(R.id.editTextCodigo);


        return vista;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(Plantillafragment1ViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btn1=view.findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                //ejecutarServicio("http://192.168.56.1/wappservice/insertar_ficha.php");
                codigoTXT = (EditText) getView().findViewById(R.id.editTextCodigo);
                String codigo = codigoTXT.getText().toString();
                Bundle bundle = new Bundle();
                Toast.makeText(getActivity(), codigoTXT.getText().toString() , Toast.LENGTH_SHORT).show();
                bundle.putString("amount", codigo);
                ejecutarServicio("http://192.168.56.1/wappservice/insertar_ficha.php");

                Navigation.findNavController(v).navigate(R.id.planillaFragment2, bundle);
            }
        });
    }
    private void ejecutarServicio(String URL){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getActivity(), "PASO "+ codigoTXT.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "No Paso", Toast.LENGTH_SHORT).show();
            }
        }){
            protected Map<String, String>getParams() throws AuthFailureError{
                Map<String,String> parametros=new HashMap<String, String>();
                parametros.put("codigo",codigoTXT.getText().toString());
                return  parametros;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this.getContext());
        requestQueue.add(stringRequest);
    }

}