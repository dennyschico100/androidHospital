package com.example.hospital;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import datos.ConexionSqlLite;

public class PersonalMedicoRecycler extends RecyclerView.Adapter<PersonalMedicoRecycler.ViewHolder>
{
    private RecyclerViewInterface recyclerViewInterface;

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView NombreDr, Especialidad, NumeroT;

        public ViewHolder(View itemView)
        {
            super(itemView);
            NombreDr = itemView.findViewById(R.id.NombreDr);
            Especialidad = itemView.findViewById(R.id.Especialidad);
            NumeroT = itemView.findViewById(R.id.NumeroT);

            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    recyclerViewInterface.OnItemClick(getAdapterPosition());
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener()
            {
                @Override
                public boolean onLongClick(View view)
                {
                    recyclerViewInterface.OnLongItemClick(getAdapterPosition());
                    return true;
                }
            });
        }
    }

    public List<Usuarios> ListaDoctoresLibres;

    public PersonalMedicoRecycler(List<Usuarios> ListaDoctoresLibres, RecyclerViewInterface recyclerViewInterface)
    {
        this.ListaDoctoresLibres = ListaDoctoresLibres;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int ViewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contenedor_docs, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    public void onBindViewHolder(ViewHolder holder, int position)
    {
        holder.NombreDr.setText(ListaDoctoresLibres.get(position).getNombres() +" " +ListaDoctoresLibres.get(position).getApellidos());
        holder.Especialidad.setText("JVPM: " +ListaDoctoresLibres.get(position).getJvmp());
        holder.NumeroT.setText("Telefono:" +ListaDoctoresLibres.get(position).getTelefono());
    }

    @Override
    public int getItemCount()
    {
        return ListaDoctoresLibres.size();
    }

}
