package com.CDH.myapplication.ui.fragments;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

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


public class plantillafragment1_buscar extends Fragment {
    EditText codigoTXT,fechaTXT,acargoTXT,asignadaTXT,detalleTXT,proyectoTXT;
    DbHelper FavDB;
    private Plantillafragment1ViewModel mViewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista= inflater.inflate(R.layout.fragment_plantillafragment1_buscar, container, false);
        codigoTXT = (EditText) vista.findViewById(R.id.editTextCodigo);
        fechaTXT = (EditText) vista.findViewById(R.id.editTextDate);
        acargoTXT = (EditText) vista.findViewById(R.id.editTextPersona);
        proyectoTXT = (EditText) vista.findViewById(R.id.editTextProyecto);
        asignadaTXT = (EditText) vista.findViewById(R.id.editTextSuma);
        detalleTXT = (EditText) vista.findViewById(R.id.editTextDetalle);
        FavDB = new DbHelper(vista.getContext());
        Bundle bundle = getArguments();
        if(bundle!=null){
            String codigo = getArguments().getString("codigo");
            buscaFactura("http://192.168.56.1/wappservice/buscar_ficha.php?codigo="+codigo+"");
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

        Button btn1 = view.findViewById(R.id.btn12);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*ejecutarServicio("http://192.168.56.1/wappservice/insertar_ficha.php");
                 */
                String codigo = codigoTXT.getText().toString();
                Bundle bundle = new Bundle();
                bundle.putString("codigo", codigo); bundle.putString("mod", "1");
                ejecutarServicio("http://192.168.56.1/wappservice/modifica_ficha.php");
                Navigation.findNavController(v).navigate(R.id.planillaFragment2,bundle);
            }
        });

        Button btn2 = view.findViewById(R.id.btnBuscar);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String codigo = codigoTXT.getText().toString();
                buscaFactura("http://192.168.56.1/wappservice/buscar_ficha.php?codigo="+codigo+"");
            }
        });
    }


    private void ejecutarServicio(String URL){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getActivity(), "PASO ", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "No Paso", Toast.LENGTH_SHORT).show();
            }
        }){
            protected Map<String, String> getParams() throws AuthFailureError {
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
                        codigoTXT.setText(jsonObject.getString("codigoServicio"));
                        fechaTXT.setText(jsonObject.getString("fecha"));
                        acargoTXT.setText(jsonObject.getString("personaCargo"));
                        proyectoTXT.setText(jsonObject.getString("Proyecto"));
                        asignadaTXT.setText(jsonObject.getString("personaCargo"));
                        detalleTXT.setText(jsonObject.getString("Detalle"));
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