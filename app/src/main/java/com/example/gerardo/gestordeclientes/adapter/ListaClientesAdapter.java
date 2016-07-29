package com.example.gerardo.gestordeclientes.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gerardo.gestordeclientes.R;
import com.example.gerardo.gestordeclientes.model.Cliente;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Gerardo on 27/07/2016.
 */
public class ListaClientesAdapter extends RecyclerView.Adapter<ListaClientesAdapter.ClienteViewHolder>
        implements View.OnClickListener{


    Context context;
    ArrayList<Cliente> listaClientes;

    private View.OnClickListener listener;

    public ListaClientesAdapter(Context context, ArrayList<Cliente> listaClientes) {
        this.context = context;
        this.listaClientes = listaClientes;
    }

    @Override
    public ClienteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_cliente_main,parent,false);
        itemView.setOnClickListener(this);
        return new ClienteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ClienteViewHolder holder, int position) {
        Cliente currentClient = listaClientes.get(position);
        holder.setNombreCliente(currentClient.getNombre()+" "+currentClient.getApellido());
        holder.setRutCliente(currentClient.getRut());
        holder.setMarcaCliente(currentClient.getMoto().getMarca().getNombreMarca());
        holder.setModeloCliente(currentClient.getMoto().getModelo().getNombreModelo());
    }

    @Override
    public int getItemCount() {
        return listaClientes.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }
    @Override
    public void onClick(View v) {
        if(listener != null){
            listener.onClick(v);
        }
    }
    public String getRutCliente(int posicion){
        Cliente current = listaClientes.get(posicion);
        return current.getRut();
    }


    public class ClienteViewHolder extends RecyclerView.ViewHolder{

        @Bind(R.id.item_cliente_nombre)
        TextView nombreCliente;
        @Bind(R.id.item_cliente_rut)
        TextView rutCliente;
        @Bind(R.id.item_cliente_marca)
        TextView marcaCliente;
        @Bind(R.id.item_cliente_modelo)
        TextView modeloCliente;

        public ClienteViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void setNombreCliente(String nombreCliente) {
            this.nombreCliente.setText(nombreCliente);
        }

        public void setRutCliente(String rutCliente) {
            this.rutCliente.setText(context.getResources().getString(R.string.item_cliente_rut,rutCliente));
        }

        public void setMarcaCliente(String marcaCliente) {
            this.marcaCliente.setText(context.getResources().getString(R.string.item_cliente_marca,marcaCliente));
        }

        public void setModeloCliente(String modeloCliente) {
            this.modeloCliente.setText(context.getResources().getString(R.string.item_cliente_modelo,modeloCliente));
        }
    }

}
