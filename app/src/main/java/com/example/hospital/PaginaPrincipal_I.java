package com.example.hospital;

import androidx.appcompat.app.AppCompatActivity;
import datos.ConexionSqlLite;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class PaginaPrincipal_I extends AppCompatActivity {
    private TextView fecha,camasOcupadas, camasDisponibles;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina_principal__i);
        fecha = (TextView)  findViewById(R.id.tvFecha);
        camasOcupadas= (TextView) findViewById(R.id.tvCamasDisponibles);
        camasDisponibles=(TextView) findViewById(R.id.tvCamasOcupadas);

        Calendar calendar = Calendar.getInstance();
        fecha.setText(calendar.getTime().toString());
        getDatosInicio();



    }
    public void irPacientes(View v)
    {
        Bundle parametros = this.getIntent().getExtras();
        int id = parametros.getInt("id");
        int rol = parametros.getInt("rol");
        System.out.println(rol);
        Toast.makeText(this, ""+rol, Toast.LENGTH_SHORT).show();
        if(rol==3)
        {
            Intent i=new Intent(this, PacientesAdministracion_i.class);
            i.putExtra("rol",rol);
            startActivity(i);
        }else{
            Intent i=new Intent(this, PacientesDoctores.class);
            i.putExtra("rol",rol);
            startActivity(i);
        }

    }
    public void irPersonalMedico(View v)
    {
        Intent i=new Intent(this, PersonalMedico.class);
        startActivity(i);
    }
    public void irEditarPerfil(View v)
    {
        Bundle parametros = this.getIntent().getExtras();
        int id = parametros.getInt("id");
        int rol = parametros.getInt("rol");
        Intent i=new Intent(this, EditarperfilDoc.class);
        i.putExtra("id",id);
        startActivity(i);
    }
    public void getDatosInicio()
    {
        String consulta = "select * from habitaciones";
        Cursor datos = getConexion().rawQuery(consulta, null);
        int camasDisponibles1=0;
        int totalCamas=0;
        int quirofanosDisponibles=0;
        while(datos.moveToNext())
        {
           camasDisponibles1=camasDisponibles1+datos.getInt(3);
           totalCamas = totalCamas + datos.getInt(2);
           if(datos.getString(4).toUpperCase().equals("quirofano".toUpperCase()))
           {
               quirofanosDisponibles=quirofanosDisponibles+camasDisponibles1+datos.getInt(3);
           }
        }
        camasDisponibles.setText(String.valueOf(camasDisponibles1));
        camasOcupadas.setText(String.valueOf(totalCamas-camasDisponibles1));
    }
    private SQLiteDatabase getConexion()
    {
        ConexionSqlLite objConexion = new ConexionSqlLite(getApplicationContext());
        return objConexion.getWritableDatabase();
    }

}