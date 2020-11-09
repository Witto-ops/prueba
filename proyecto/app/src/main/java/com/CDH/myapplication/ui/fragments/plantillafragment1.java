package com.CDH.myapplication.ui.fragments;

import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
import com.CDH.myapplication.ui.Datos.DbHelper;
import com.CDH.myapplication.ui.vistas.Plantillafragment1ViewModel;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class plantillafragment1 extends Fragment {

    private Plantillafragment1ViewModel mViewModel;
    DbHelper FavDB;
    EditText codigoTXT,fechaTXT,acargoTXT,asignadaTXT,detalleTXT,proyectoTXT;

    public static plantillafragment1 newInstance() {
        return new plantillafragment1();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View vista= inflater.inflate(R.layout.plantillafragment1_fragment, container, false);
        codigoTXT = (EditText) vista.findViewById(R.id.editTextCodigo);
        fechaTXT = (EditText) vista.findViewById(R.id.editTextDate);
        acargoTXT = (EditText) vista.findViewById(R.id.editTextPersona);
        proyectoTXT = (EditText) vista.findViewById(R.id.editTextProyecto);
        asignadaTXT = (EditText) vista.findViewById(R.id.editTextSuma);
        detalleTXT = (EditText) vista.findViewById(R.id.editTextDetalle);

        asignadaTXT.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if(asignadaTXT.getText().toString().equals("")){
                        asignadaTXT.setText("0");
                    }
                }
            }
        });
        FavDB = new DbHelper(vista.getContext());
        Bundle bundle = getArguments();
        if(bundle!=null){
            agregabdd(getArguments().getString("codigo"));
        }
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

                /*ejecutarServicio("http://192.168.56.1/wappservice/insertar_ficha.php");
               // codigoTXT = (EditText) getView().findViewById(R.id.editTextCodigo);
                String codigo = codigoTXT.getText().toString();
                String fecha = fechaTXT.getText().toString();
                String acargo = acargoTXT.getText().toString();
                String proyecto = proyectoTXT.getText().toString();
                String asignada = asignadaTXT.getText().toString();
                String detalle = detalleTXT.getText().toString();*/


                String codigo = codigoTXT.getText().toString();


                Bundle bundle = new Bundle();
              //  Toast.makeText(getActivity(), codigoTXT.getText().toString() , Toast.LENGTH_SHORT).show();
                bundle.putString("codigo", codigo); bundle.putString("numero", "1");
                agregabdd(codigo);
                //ejecutarServicio("http://192.168.56.1/wappservice/insertar_ficha.php");

                Navigation.findNavController(v).navigate(R.id.planillaFragment2, bundle);
            }
        });

    }
    private void agregabdd(String codigo){
        String fecha = fechaTXT.getText().toString();
        String acargo = acargoTXT.getText().toString();
        String proyecto = proyectoTXT.getText().toString();
        String asignada = asignadaTXT.getText().toString();
        String detalle = detalleTXT.getText().toString();
        if(FavDB.if_exist(codigo)){
            SQLiteDatabase db = FavDB.getReadableDatabase();
            Cursor cursor = FavDB.select_all_favorite_list();
            try {
                while (cursor.moveToNext()) {
                    fechaTXT.setText(cursor.getString(cursor.getColumnIndex(FavDB.FECHA)));
                    acargoTXT.setText(cursor.getString(cursor.getColumnIndex(FavDB.ACARGO)));
                    proyectoTXT.setText(cursor.getString(cursor.getColumnIndex(FavDB.PROYECTO)));
                    asignadaTXT.setText(cursor.getString(cursor.getColumnIndex(FavDB.ASIGNADA)));
                    detalleTXT.setText(cursor.getString(cursor.getColumnIndex(FavDB.DETALLE)));
                    codigoTXT.setText(cursor.getString(cursor.getColumnIndex(FavDB.CODIGO)));

                }
                FavDB.edit_parts_one(codigo, acargo,  proyecto,  asignada,  detalle,  fecha);

            } finally {
                if (cursor != null && cursor.isClosed())
                    cursor.close();
                db.close();
            }


        }else{
            FavDB.insertIntoTheDatabase(codigo, acargo,  proyecto,  asignada,  detalle,  fecha,  "true");
        }

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
                parametros.put("fecha",fechaTXT.getText().toString());
                parametros.put("acargo",acargoTXT.getText().toString());
                parametros.put("proyecto",proyectoTXT.getText().toString());
                parametros.put("asignada",asignadaTXT.getText().toString());
                parametros.put("detalle",detalleTXT.getText().toString());
                return  parametros;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this.getContext());
        requestQueue.add(stringRequest);
    }


    private void buscaFactura(String URL){
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        //codigoTXT.setText(jsonObject.getString(""));
                        fechaTXT.setText(jsonObject.getString(""));
                        acargoTXT.setText(jsonObject.getString(""));
                        proyectoTXT.setText(jsonObject.getString(""));
                        asignadaTXT.setText(jsonObject.getString(""));
                        detalleTXT.setText(jsonObject.getString(""));
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