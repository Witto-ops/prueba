package com.CDH.myapplication.ui.fragments;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.CDH.myapplication.R;
import com.CDH.myapplication.ui.Datos.DbHelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ResumenFragment extends Fragment {
    private ListView lista,lista2,lista3;
    List<String> items,items2,items3;
    ArrayAdapter ADP,ADP2,ADP3;
    TextView desayunotxt, almuerzotxt, cenatxt, aguatxt, alojamientotxt, combustibletxt,peajetxt, estacionamientotxt;
    TextView codigoTXT,fechaTXT,acargoTXT,asignadaTXT,detalleTXT,proyectoTXT;
    TextView total1, total2;
    DbHelper FavDB;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista= inflater.inflate(R.layout.fragment_resumen, container, false);

        codigoTXT = (TextView) vista.findViewById(R.id.textCodigo);
        fechaTXT = (TextView) vista.findViewById(R.id.textFecha);
        acargoTXT = (TextView) vista.findViewById(R.id.textPersona);
        proyectoTXT = (TextView) vista.findViewById(R.id.textProyecto);
        asignadaTXT = (TextView) vista.findViewById(R.id.textSuma);
        detalleTXT = (TextView) vista.findViewById(R.id.textDetalle);

        total1 = (TextView) vista.findViewById(R.id.textNumero);
        total2 = (TextView) vista.findViewById(R.id.textNumero2);

        desayunotxt = (TextView) vista.findViewById(R.id.textDesayuno);
        almuerzotxt = (TextView) vista.findViewById(R.id.textAlmuerzo);
        cenatxt = (TextView) vista.findViewById(R.id.textCena);
        aguatxt = (TextView) vista.findViewById(R.id.textAgua);
        alojamientotxt = (TextView) vista.findViewById(R.id.textAlojamiento);
        combustibletxt = (TextView) vista.findViewById(R.id.textCombustible);
        peajetxt =(TextView) vista.findViewById(R.id.textPeaje);
        estacionamientotxt = (TextView) vista.findViewById(R.id.textEstacionamiento);

        FavDB = new DbHelper(vista.getContext());

        Bundle bundle = getArguments();
        if(bundle!=null ){

            rellena(getArguments().getString("codigo"));

            if(getArguments().getSerializable("objetos")!=null){
                items=(ArrayList) getArguments().getSerializable("objetos");
                lista = vista.findViewById(R.id.lista);
                ADP = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,items);
                lista.setAdapter(ADP);
                ADP.notifyDataSetChanged();

            }
            if(getArguments().getSerializable("objetos3")!=null){
                items3=(ArrayList) getArguments().getSerializable("objetos3");
                lista3 = vista.findViewById(R.id.lista3);
                ADP3 = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,items3);
                lista3.setAdapter(ADP3);
                ADP3.notifyDataSetChanged();

            } if(getArguments().getSerializable("objetos2")!=null){
                items2=(ArrayList) getArguments().getSerializable("objetos2");
                lista2 = vista.findViewById(R.id.lista2);
                ADP2 = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,items2);
                lista2.setAdapter(ADP2);
                ADP2.notifyDataSetChanged();
            }

        }
        total1.setText(suma());
        total2.setText(suma2());
        return vista;
    }

    private void rellena(String codigo){
        if (FavDB.if_exist(codigo)) {

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

                        desayunotxt.setText(cursor.getString(cursor.getColumnIndex(FavDB.DESAYUNO)));
                        almuerzotxt.setText(cursor.getString(cursor.getColumnIndex(FavDB.ALMUERZO)));
                        cenatxt.setText(cursor.getString(cursor.getColumnIndex(FavDB.CENA)));
                        aguatxt.setText(cursor.getString(cursor.getColumnIndex(FavDB.AGUA)));
                        alojamientotxt.setText(cursor.getString(cursor.getColumnIndex(FavDB.ALOJAMIENTO)));
                        combustibletxt.setText(cursor.getString(cursor.getColumnIndex(FavDB.COMBUSTIBLE)));
                        peajetxt.setText(cursor.getString(cursor.getColumnIndex(FavDB.PEAJE)));
                        estacionamientotxt.setText(cursor.getString(cursor.getColumnIndex(FavDB.ESTACIONAMIENTO)));

                    }

            } finally {
                if (cursor != null && cursor.isClosed())
                    cursor.close();
                db.close();
            }
        }
    }
    private String suma(){
        int desayuno = Integer.parseInt(desayunotxt.getText().toString());
        int almuerzo = Integer.parseInt(almuerzotxt.getText().toString());
        int cena = Integer.parseInt(cenatxt.getText().toString());
        int agua = Integer.parseInt(aguatxt.getText().toString());
        int alojamiento = Integer.parseInt(alojamientotxt.getText().toString());

        int total=desayuno+almuerzo+cena+agua+alojamiento;

        return String.valueOf(total);
    }

    private String suma2(){
        int combustible = Integer.parseInt(combustibletxt.getText().toString());
        int peaje = Integer.parseInt(peajetxt.getText().toString());
        int estacionamiento = Integer.parseInt(estacionamientotxt.getText().toString());

        int total=combustible+peaje+estacionamiento;

        return String.valueOf(total);
    }

}