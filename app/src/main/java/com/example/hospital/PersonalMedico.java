package com.example.hospital;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
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

public class PersonalMedico extends AppCompatActivity implements RecyclerViewInterface
{
    private RecyclerView RecyclerDocs;
    private PersonalMedicoRecycler AdaptadorMedicos;
    private TextView DocsDisponibles;
    private EditText BuscarText;
    private Button BuscarMed;
    private List<Usuarios> Doctores = new ArrayList<>();

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
            DocsDisponibles.setText("Personal médico disponible: " +CantDocs.getInt(0));
        }

        RecyclerDocs = findViewById(R.id.RecyclerDocs);
        RecyclerDocs.setLayoutManager(new LinearLayoutManager(this));
        AdaptadorMedicos = new PersonalMedicoRecycler(GetDocs(), this);
        RecyclerDocs.setAdapter(AdaptadorMedicos);


        BuscarMed.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(Validar())
                {
                    CargarBusqueda();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Ingrese el nombre del medico a buscar..." , Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void CargarBusqueda()
    {
        ConexionSqlLite objConexion = new ConexionSqlLite(getApplicationContext());
        final SQLiteDatabase objBase = objConexion.getWritableDatabase();

        String sql="select * from usuarios where nombres || ' ' || apellidos = '"+BuscarText.getText().toString()+"'";
        Cursor Consulta = objBase.rawQuery(sql, null);
        List<Usuarios> DoctoresB = new ArrayList<>();

        while(Consulta.moveToNext())
        {
            DoctoresB.add(new Usuarios(Consulta.getString(1) , Consulta.getString(2),
                    Consulta.getString(3), Consulta.getString(4) , Consulta.getInt(5),
                    Consulta.getInt(6), Consulta.getInt(7), Consulta.getString(8), Consulta.getInt(9)));
        }

        String CantBusqueda = "select count(*) from usuarios where nombres || ' ' || apellidos = '"+BuscarText.getText().toString()+"'";
        Cursor CantDocs = objBase.rawQuery(CantBusqueda, null);

        if(CantDocs.moveToNext())
        {
            DocsDisponibles.setText("Resulado de la busqueda: " +CantDocs.getInt(0));
        }

        AdaptadorMedicos = new PersonalMedicoRecycler(DoctoresB, this );
        RecyclerDocs.setAdapter(AdaptadorMedicos);
    }

    public List<Usuarios> GetDocs()
    {
        ConexionSqlLite objConexion = new ConexionSqlLite(getApplicationContext());
        final SQLiteDatabase objBase = objConexion.getWritableDatabase();

        String ConsultarDocs = "SELECT * FROM usuarios where rol = 1 or rol = 2";
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

    @Override
    public void OnItemClick(int position)
    {
        Intent SelectMed = new Intent(getApplicationContext(), SeleccionarMedico.class);
        SelectMed.putExtra("DoctorSelecionado" , Doctores.get(position).getNombres() +" " +Doctores.get(position).getApellidos());
        SelectMed.putExtra("Correo", Doctores.get(position).getEmail());
        SelectMed.putExtra("Telefono" , Doctores.get(position).getTelefono());
        SelectMed.putExtra("JVPM", Doctores.get(position).getJvmp());
        SelectMed.putExtra("Edad", Doctores.get(position).getEdad());
        startActivity(SelectMed);
    }

    @Override
    public void OnLongItemClick(int position)
    {

    }
}