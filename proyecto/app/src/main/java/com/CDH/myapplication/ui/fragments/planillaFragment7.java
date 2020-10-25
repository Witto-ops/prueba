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
import android.widget.EditText;
import android.widget.Toast;

import com.CDH.myapplication.R;
import com.CDH.myapplication.ui.Datos.DbHelper;
import com.CDH.myapplication.ui.Datos.DbHelperPersonal;
import com.CDH.myapplication.ui.vistas.PlanillaFragment7ViewModel;

public class planillaFragment7 extends Fragment {

    private PlanillaFragment7ViewModel mViewModel;
    EditText nombretxt, ruttxt, samtxt, spmtxt, eamtxt, epmtxt;
    DbHelperPersonal FavDB;
    Button btn, btn2;

    public static planillaFragment7 newInstance() {
        return new planillaFragment7();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
       View vista= inflater.inflate(R.layout.planilla_fragment7_fragment, container, false);
        nombretxt = (EditText) vista.findViewById(R.id.editTextNombre);
        ruttxt = (EditText) vista.findViewById(R.id.editTextRut);
        samtxt = (EditText) vista.findViewById(R.id.editTextSalidaMañana);
        spmtxt = (EditText) vista.findViewById(R.id.editTextSalidaTarde);
        eamtxt = (EditText) vista.findViewById(R.id.editTextRetornoMañana);
        epmtxt = (EditText) vista.findViewById(R.id.editTextRetornoTarde);
        btn = (Button) vista.findViewById(R.id.button2);
        btn2 =(Button) vista.findViewById(R.id.button3);
        FavDB = new DbHelperPersonal(vista.getContext());
        Bundle bundle = getArguments();
        if(bundle!=null && getArguments().getString("n").equals("2")){
            rellena(getArguments().getString("rut"),getArguments().getString("nombre"),getArguments().getString("eam"),
                    getArguments().getString("epm"),getArguments().getString("sam"),getArguments().getString("spm"));
            btn2.setVisibility(View.VISIBLE);
        }else{
            btn.setText("Guardar");

            btn2.setVisibility(View.GONE);

        }


       return  vista;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button button5=view.findViewById(R.id.button2);
        button5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String codigo = getArguments().getString("codigo");
                Bundle bundle = new Bundle();
                bundle.putString("codigo", codigo);
                if(btn.getText().toString().equals("Modificar")){
                    modificar(getArguments().getString("rut"));

                }else{
                    agregar();
                }
                Navigation.findNavController(v).navigate(R.id.personalFragment,bundle);
            }
        });

        Button btn3=view.findViewById(R.id.button3);
        btn3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String codigo = getArguments().getString("codigo");
                Bundle bundle = new Bundle();
                bundle.putString("codigo", codigo);
                elimina(getArguments().getString("rut"));
                Navigation.findNavController(v).navigate(R.id.personalFragment,bundle);
            }
        });
    }

    public void agregar(){
        if(!nombretxt.getText().toString().equals("") && !ruttxt.getText().toString().equals("") && !samtxt.getText().toString().equals("")
        && !spmtxt.getText().toString().equals("") && !eamtxt.getText().toString().equals("") && !epmtxt.getText().toString().equals("")) {
            String nombre = nombretxt.getText().toString();
            String rut = ruttxt.getText().toString();
            String sam = samtxt.getText().toString();
            String spm = spmtxt.getText().toString();
            String eam = eamtxt.getText().toString();
            String epm = epmtxt.getText().toString();
            if (!FavDB.is_exist(rut)) {
                FavDB.insertIntoTheDatabase(nombre, rut, sam, spm, eam, epm, "true");
                limpia();
            }else{
                FavDB.cambio(rut);
            }
        }
    }
    public void limpia(){
        nombretxt.setText("");
        ruttxt.setText("");
        samtxt.setText("");
        spmtxt.setText("");
        eamtxt.setText("");
        epmtxt.setText("");
        Toast.makeText(getActivity(), "Se agrego el Personal", Toast.LENGTH_SHORT).show();

    }

    public void elimina(String rut){
        if(!nombretxt.getText().toString().equals("") && !ruttxt.getText().toString().equals("") && !samtxt.getText().toString().equals("")
                && !spmtxt.getText().toString().equals("") && !eamtxt.getText().toString().equals("") && !epmtxt.getText().toString().equals("")
                && ruttxt.getText().toString().equals(rut)) {
            String rut2 = ruttxt.getText().toString();
            FavDB.remove_fav(rut2);
            Toast.makeText(getActivity(), "Se Elimino el Personal", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getActivity(), "No se Elimino el Personal", Toast.LENGTH_SHORT).show();
        }


    }
    public void modificar(String rut){
        if(!nombretxt.getText().toString().equals("") && !ruttxt.getText().toString().equals("") && !samtxt.getText().toString().equals("")
                && !spmtxt.getText().toString().equals("") && !eamtxt.getText().toString().equals("") && !epmtxt.getText().toString().equals("")
                && ruttxt.getText().toString().equals(rut)) {
            String nombre = nombretxt.getText().toString();
            String rut2 = ruttxt.getText().toString();
            String sam = samtxt.getText().toString();
            String spm = spmtxt.getText().toString();
            String eam = eamtxt.getText().toString();
            String epm = epmtxt.getText().toString();
            FavDB.update(nombre, rut2, sam, spm, eam, epm);
            Toast.makeText(getActivity(), "Se modifico el Personal", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getActivity(), "No se modifico el Personal", Toast.LENGTH_SHORT).show();
        }
    }

    public void rellena(String rut, String nombre, String eam, String epm, String sam, String spm){
        nombretxt.setText(nombre);
        ruttxt.setText(rut);
        samtxt.setText(sam);
        spmtxt.setText(spm);
        eamtxt.setText(eam);
        epmtxt.setText(epm);
        btn.setText("Modificar");
        btn2.setVisibility(View.VISIBLE);
    }
}