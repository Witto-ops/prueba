package com.CDH.myapplication.ui.fragments;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.CDH.myapplication.R;
import com.CDH.myapplication.ui.Adaptador.AdaptadorPersonal;
import com.CDH.myapplication.ui.Datos.DbHelper;
import com.CDH.myapplication.ui.Datos.DbHelperPersonal;
import com.CDH.myapplication.ui.clases.Personal;

import java.util.ArrayList;


public class personalFragment extends Fragment {

    AdaptadorPersonal adaptadorPersonal;
    RecyclerView recyclerViewPersonal;
    ArrayList<Personal> listaPersonal;
    DbHelperPersonal FavDB;
    EditText codigotxt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista= inflater.inflate(R.layout.fragment_personal, container, false);
        recyclerViewPersonal = vista.findViewById(R.id.recyclerIdp);
        listaPersonal = new ArrayList<>();
        codigotxt = (EditText) vista.findViewById(R.id.editTextCodigo);
        FavDB = new DbHelperPersonal(vista.getContext());
        cargarlista();



        return  vista;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btn1=view.findViewById(R.id.button3);
        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // String codigo = codigotxt.getText().toString();
                //Bundle bundle = new Bundle();
               // bundle.putString("codigo", codigo);
                Navigation.findNavController(v).navigate(R.id.planillaFragment7);
            }
        });

    }
    public void cargarlista() {
        if (listaPersonal != null) {
            listaPersonal.clear();
        }
        SQLiteDatabase db = FavDB.getReadableDatabase();
        Cursor cursor = FavDB.select_all_favorite_list();
        try {
            while (cursor.moveToNext()) {
                        Personal a = new Personal();
                        a.setNombre(cursor.getString(cursor.getColumnIndex(FavDB.NOMBRE)));
                        a.setRut(cursor.getString(cursor.getColumnIndex(FavDB.RUT)));
                        a.setEntradaAm(cursor.getString(cursor.getColumnIndex(FavDB.EAM)));
                        a.setEntradaPm(cursor.getString(cursor.getColumnIndex(FavDB.EPM)));
                        a.setSalidaAm(cursor.getString(cursor.getColumnIndex(FavDB.SAM)));
                        a.setSalidaPm(cursor.getString(cursor.getColumnIndex(FavDB.SPM)));
                        listaPersonal.add(a);
                        mostrarData();
                    }
        } finally {
            if (cursor != null && cursor.isClosed())
                cursor.close();
            db.close();
        }
    }

    //Despliega la lista de los productos en el recyclerView
    public void mostrarData() {

        recyclerViewPersonal.setLayoutManager(new LinearLayoutManager(getContext()));
        adaptadorPersonal = new AdaptadorPersonal(getContext(), listaPersonal);
        recyclerViewPersonal.setAdapter(adaptadorPersonal);

        /*Cuando se haga click en el recyclerView , se encarga de mostrar un Toast con el nombre del local
            Envia el objeto seleccionado por medio de un bundle al activity DetalleLocal
         */
        adaptadorPersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = listaPersonal.get(recyclerViewPersonal.getChildAdapterPosition(v)).getNombre();
                Toast.makeText(v.getContext(), "selecciono: " + nombre, Toast.LENGTH_SHORT).show();

                Bundle bundle = new Bundle();
                //  Toast.makeText(getActivity(), codigoTXT.getText().toString() , Toast.LENGTH_SHORT).show();
                bundle.putString("rut", listaPersonal.get(recyclerViewPersonal.getChildAdapterPosition(v)).getRut());
                bundle.putString("nombre", listaPersonal.get(recyclerViewPersonal.getChildAdapterPosition(v)).getNombre());
                bundle.putString("eam", listaPersonal.get(recyclerViewPersonal.getChildAdapterPosition(v)).getEntradaAm());
                bundle.putString("epm", listaPersonal.get(recyclerViewPersonal.getChildAdapterPosition(v)).getEntradaPm());
                bundle.putString("sam", listaPersonal.get(recyclerViewPersonal.getChildAdapterPosition(v)).getSalidaAm());
                bundle.putString("spm", listaPersonal.get(recyclerViewPersonal.getChildAdapterPosition(v)).getSalidaPm());

                Navigation.findNavController(v).navigate(R.id.planillaFragment7, bundle);

            }
        });
    }
}