package com.CDH.myapplication.ui.fragments;

import androidx.lifecycle.ViewModelProviders;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.CDH.myapplication.R;
import com.CDH.myapplication.ui.Datos.DbHelper;
import com.CDH.myapplication.ui.vistas.PlanillaFragment2ViewModel;

public class planillaFragment2 extends Fragment {

    private PlanillaFragment2ViewModel mViewModel;
    EditText desayunotxt, almuerzotxt, cenatxt, aguatxt, alojamientotxt, combustibletxt,peajetxt, estacionamientotxt;
    TextView total1, total2;
    DbHelper FavDB;

    public static planillaFragment2 newInstance() {
        return new planillaFragment2();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View vista= inflater.inflate(R.layout.planilla_fragment2_fragment, container, false);

        desayunotxt = (EditText) vista.findViewById(R.id.editTextDesayuno);
        almuerzotxt = (EditText) vista.findViewById(R.id.editTextAlmuerzo);
        cenatxt = (EditText) vista.findViewById(R.id.editTextCena);
        aguatxt = (EditText) vista.findViewById(R.id.editTextAgua);
        alojamientotxt = (EditText) vista.findViewById(R.id.editTextAlojamiento);
        combustibletxt = (EditText) vista.findViewById(R.id.editTextCombustible);
        peajetxt =(EditText) vista.findViewById(R.id.editTextPeaje);
        estacionamientotxt = (EditText) vista.findViewById(R.id.editTextEstacionamiento);

       /* desayunotxt.setText("0");
        almuerzotxt.setText("0");
        cenatxt.setText("0");
        estacionamientotxt.setText("0");
        aguatxt.setText("0");
        alojamientotxt.setText("0");
        combustibletxt.setText("0");
        peajetxt.setText("0");*/





        total1 = (TextView) vista.findViewById(R.id.textNumero);
        total2 = (TextView) vista.findViewById(R.id.textNumero2);
        FavDB = new DbHelper(vista.getContext());

        Bundle bundle = getArguments();
        if(bundle!=null ){
            rellena(getArguments().getString("codigo"));
        }

        desayunotxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if(desayunotxt.getText().toString().equals("")){
                        desayunotxt.setText("0");

                    }else {
                        total1.setText(suma());
                    }
                }
            }
        });
        almuerzotxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if(almuerzotxt.getText().toString().equals("")){
                        almuerzotxt.setText("0");

                    }else {
                        total1.setText(suma());
                    }
                }
            }
        });
        cenatxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if(cenatxt.getText().toString().equals("")){
                        cenatxt.setText("0");

                    }else {
                        total1.setText(suma());
                    }
                }
            }
        });
        aguatxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if(aguatxt.getText().toString().equals("")){
                        aguatxt.setText("0");

                    }else {
                        total1.setText(suma());
                    }
                }
            }
        });
        alojamientotxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if(alojamientotxt.getText().toString().equals("")){
                        alojamientotxt.setText("0");
                    }else {
                        total1.setText(suma());
                    }
                }
            }
        });
        combustibletxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if(combustibletxt.getText().toString().equals("")){
                        combustibletxt.setText("0");
                    }else {
                        total2.setText(suma2());
                    }
                }
            }
        });
        peajetxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if(combustibletxt.getText().toString().equals("")){
                        combustibletxt.setText("0");
                    }else {
                        total2.setText(suma2());
                    }
                }
            }
        });
        estacionamientotxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if(combustibletxt.getText().toString().equals("")){
                        combustibletxt.setText("0");
                    }else {
                        total2.setText(suma2());
                    }
                }
            }
        });


        return  vista;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(PlanillaFragment2ViewModel.class);
        // TODO: Use the ViewModel
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button button6=view.findViewById(R.id.btnA1);
        button6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String codigo = getArguments().getString("codigo");
                Bundle bundle = new Bundle();
                bundle.putString("codigo", codigo);
                agregabdd(codigo);

                Navigation.findNavController(v).navigate(R.id.plantillafragment1,bundle);
            }
        });

        Button button5=view.findViewById(R.id.btnS1);
        button5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String codigo = getArguments().getString("codigo");
                Bundle bundle = new Bundle();
                bundle.putString("codigo", codigo);
                agregabdd(codigo);

                Navigation.findNavController(v).navigate(R.id.planillaFragment3,bundle);
            }
        });


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

    private void rellena(String codigo){
        if (FavDB.if_exist(codigo)) {

            SQLiteDatabase db = FavDB.getReadableDatabase();
            Cursor cursor = FavDB.select_all_favorite_list();
            try {
                while (cursor.moveToNext()) {
                    if(cursor.getString(cursor.getColumnIndex(FavDB.DESAYUNO))==null && cursor.getString(cursor.getColumnIndex(FavDB.ALMUERZO))== null && cursor.getString(cursor.getColumnIndex(FavDB.CENA))==null &&
                    cursor.getString(cursor.getColumnIndex(FavDB.AGUA))==null && cursor.getString(cursor.getColumnIndex(FavDB.ALOJAMIENTO))==null && cursor.getString(cursor.getColumnIndex(FavDB.COMBUSTIBLE))==null && cursor.getString(cursor.getColumnIndex(FavDB.PEAJE))==null &&
                    cursor.getString(cursor.getColumnIndex(FavDB.ESTACIONAMIENTO))== null){
                        desayunotxt.setText("0");
                        almuerzotxt.setText("0");
                        cenatxt.setText("0");
                        estacionamientotxt.setText("0");
                        aguatxt.setText("0");
                        alojamientotxt.setText("0");
                        combustibletxt.setText("0");
                        peajetxt.setText("0");
                        total1.setText(suma());
                        total2.setText(suma2());
                    }else {
                        desayunotxt.setText(cursor.getString(cursor.getColumnIndex(FavDB.DESAYUNO)));
                        almuerzotxt.setText(cursor.getString(cursor.getColumnIndex(FavDB.ALMUERZO)));
                        cenatxt.setText(cursor.getString(cursor.getColumnIndex(FavDB.CENA)));
                        aguatxt.setText(cursor.getString(cursor.getColumnIndex(FavDB.AGUA)));
                        alojamientotxt.setText(cursor.getString(cursor.getColumnIndex(FavDB.ALOJAMIENTO)));
                        combustibletxt.setText(cursor.getString(cursor.getColumnIndex(FavDB.COMBUSTIBLE)));
                        peajetxt.setText(cursor.getString(cursor.getColumnIndex(FavDB.PEAJE)));
                        estacionamientotxt.setText(cursor.getString(cursor.getColumnIndex(FavDB.ESTACIONAMIENTO)));
                        total1.setText(suma());
                        total2.setText(suma2());
                    }
                    //suma

                }
            } finally {
                if (cursor != null && cursor.isClosed())
                    cursor.close();
                db.close();
            }
        }
    }

    private void agregabdd(String codigo) {
        if (!desayunotxt.getText().toString().equals("")) {
            int desayuno = Integer.parseInt(desayunotxt.getText().toString());
            int almuerzo = Integer.parseInt(almuerzotxt.getText().toString());
            int cena = Integer.parseInt(cenatxt.getText().toString());
            int agua = Integer.parseInt(aguatxt.getText().toString());
            int alojamiento = Integer.parseInt(alojamientotxt.getText().toString());
            int combustible = Integer.parseInt(combustibletxt.getText().toString());
            int peaje = Integer.parseInt(peajetxt.getText().toString());
            int estacionamiento = Integer.parseInt(estacionamientotxt.getText().toString());
            //(String codigo,int desayuno, int almuerzo, int cena, int agua, int alojamiento, int combustible, int peaje, int estacionamiento)
            FavDB.add_parts(codigo, desayuno, almuerzo, cena, agua, alojamiento, combustible, peaje, estacionamiento);


        }
    }


}