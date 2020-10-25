package com.CDH.myapplication.ui.Adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.CDH.myapplication.R;
import com.CDH.myapplication.ui.clases.Personal;

import java.util.ArrayList;

public class AdaptadorPersonal extends RecyclerView.Adapter<AdaptadorPersonal.ViewHolder>implements View.OnClickListener{
    ArrayList<Personal> listapersonal;
    private View.OnClickListener listener;
    public AdaptadorPersonal(Context context, ArrayList<Personal> listaproducto) {
        this.listapersonal=listaproducto;
    }
    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }
    public void onClick(View v){
        if(listener!=null){
            listener.onClick(v);
        }

    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_p,null,false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull AdaptadorPersonal.ViewHolder holder, int position) {
        holder.txtNombre.setText(listapersonal.get(position).getNombre());
        holder.txtRut.setText(listapersonal.get(position).getRut());
        holder.txtSalidaAm.setText(listapersonal.get(position).getSalidaAm());
        holder.txtSalidaPm.setText(listapersonal.get(position).getSalidaPm());
        holder.txtEntradaAm.setText(listapersonal.get(position).getEntradaAm());
        holder.txtEntradaPm.setText(listapersonal.get(position).getEntradaPm());



        //holder.foto.setImageResource(listapersonal.get(position).getImagenId());
       // Picasso.get().load(listapersonal.get(position).getImagen()).into(holder.foto);

        //holder.txtPrecio.setText(String.valueOf(listaproducto.get(position).getPrecio()));
    }

    @Override
    public int getItemCount() {
        return listapersonal.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNombre,txtRut, txtSalidaAm, txtSalidaPm, txtEntradaAm, txtEntradaPm;
     //   ImageView foto;

        public ViewHolder(View itemView) {
            super(itemView);
            txtNombre= (TextView) itemView.findViewById(R.id.nombre_personal);
            txtRut= (TextView) itemView.findViewById(R.id.rut_personal);
            txtSalidaAm= (TextView) itemView.findViewById(R.id.hora_salida_am);
            txtSalidaPm= (TextView) itemView.findViewById(R.id.hora_salida_pm);
            txtEntradaAm= (TextView) itemView.findViewById(R.id.hora_entrada_am);
            txtEntradaPm= (TextView) itemView.findViewById(R.id.hora_entrada_pm);
         //   foto= (ImageView) itemView.findViewById(R.id.imagen_producto);
        }
    }
}
