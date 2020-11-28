package com.example.hospital;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import datos.ConexionSqlLite;

public class PersonalMedico extends AppCompatActivity
{
    private RecyclerView RecyclerDocs;
    private PersonalMedicoRecycler AdaptadorMedicos;
    private TextView DocsDisponibles;
    private EditText BuscarText;
    private Button BuscarMed;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_medico);

        DocsDisponibles = findViewById(R.id.DocsDisponibles);
        BuscarText = findViewById(R.id.BuscarText);
        BuscarMed = findViewById(R.id.BuscarMed);

        ConexionSqlLite objConexion = new ConexionSqlLite(getApplicationContext());
        final SQLiteDatabase objBase = objConexion.getWritableDatabase();

        String CantDocsSQL = "SELECT COUNT(*) FROM usuarios where rol = 1";
        Cursor CantDocs = objBase.rawQuery(CantDocsSQL, null);

        if(CantDocs.moveToNext())
        {
            DocsDisponibles.setText("Doctores disponibles: " +CantDocs.getInt(0));
        }

        RecyclerDocs = findViewById(R.id.RecyclerDocs);
        RecyclerDocs.setLayoutManager(new LinearLayoutManager(this));
        AdaptadorMedicos = new PersonalMedicoRecycler(GetDocs());
        RecyclerDocs.setAdapter(AdaptadorMedicos);


        BuscarMed.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(Validar())
                {
                    String sql="select * from usuarios where nombres like '%"+BuscarText.getText().toString()+"%'";
                    Cursor Consulta = objBase.rawQuery(sql, null);
                    List<Usuarios> DoctoresB = new ArrayList<>();

                    while(Consulta.moveToNext())
                    {
                        DoctoresB.add(new Usuarios(Consulta.getString(1) , Consulta.getString(2),
                                Consulta.getString(3), Consulta.getString(4) , Consulta.getInt(5),
                                Consulta.getInt(6), Consulta.getInt(7), Consulta.getString(8), Consulta.getInt(9)));
                    }
                    AdaptadorMedicos = new PersonalMedicoRecycler(DoctoresB);
                    RecyclerDocs.setAdapter(AdaptadorMedicos);


                    String CantBusqueda = "Select count(*) from usuarios where rol = 1 and nombres like '%"+BuscarText.getText().toString()+"%'";
                    Cursor CantDocs = objBase.rawQuery(CantBusqueda, null);

                    if(CantDocs.moveToNext())
                    {
                        DocsDisponibles.setText("Resulado de la busqueda: " +CantDocs.getInt(0));
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Ingrese el nombre del medico a buscar..." , Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public List<Usuarios> GetDocs()
    {
        ConexionSqlLite objConexion = new ConexionSqlLite(getApplicationContext());
        final SQLiteDatabase objBase = objConexion.getWritableDatabase();
        List<Usuarios> Doctores = new ArrayList<>();

        String ConsultarDocs = "SELECT * FROM usuarios where rol = 1";
        Cursor Consulta = objBase.rawQuery(ConsultarDocs, null);

        while (Consulta.moveToNext())
        {
            Doctores.add(new Usuarios(Consulta.getString(1) , Consulta.getString(2),
                    Consulta.getString(3), Consulta.getString(4) , Consulta.getInt(5),
                    Consulta.getInt(6), Consulta.getInt(7), Consulta.getString(8), Consulta.getInt(9)));
        }
        return Doctores;
    }

    public boolean Validar()
    {
        if(!BuscarText.getText().toString().isEmpty())
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}