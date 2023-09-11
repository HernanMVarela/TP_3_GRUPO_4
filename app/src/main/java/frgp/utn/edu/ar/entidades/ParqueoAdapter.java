package frgp.utn.edu.ar.entidades;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import frgp.utn.edu.ar.controllers.R;

public class ParqueoAdapter extends BaseAdapter {
    private List<Parqueo> elementos;
    private Context context;

    public ParqueoAdapter(Context context, List<Parqueo> elementos) {
        this.context = context;
        this.elementos = elementos;
    }

    @Override
    public int getCount() {
        if(elementos != null) {
            return elementos.size();
        }else {
            return 0;
        }
    }

    @Override
    public Parqueo getItem(int position) {
        if(elementos!=null) {
            return elementos.get(position);
        } else {
            return null;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View view = convertView;
        if (convertView == null){
            view = inflater.inflate(R.layout.grid_layout,null);
        }

        TextView gvtxbPatente = (TextView) view.findViewById(R.id.gvtxbPatente);
        TextView gvtxbTiempo = (TextView) view.findViewById(R.id.gvtxbTiempo);

        gvtxbPatente.setText(getItem(position).getPatente());
        gvtxbTiempo.setText(String.valueOf(getItem(position).getTiempo()) + " Minutos");

        return view;
    }
}
