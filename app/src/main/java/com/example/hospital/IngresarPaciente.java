package com.example.hospital;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import datos.ConexionSqlLite;

public class IngresarPaciente extends AppCompatActivity
{
    private Spinner Aseguradora;
    private Button IngrPaciente;
    String[] NombresAseg = {"Seleccione..." , "ASESUISA" , "SISA" , "ISBM" , "MAPFRE" , "ACSA" , "Seguros del pacifico" , "Seguros azul" , "ASA"};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar_paciente);

        IngrPaciente = findViewById(R.id.IngrPaciente);
        Aseguradora = findViewById(R.id.Aseguradora);

        Aseguradora.setAdapter(new ArrayAdapter<>(IngresarPaciente.this , android.R.layout.simple_expandable_list_item_1 , NombresAseg));

        ConexionSqlLite objConexion = new ConexionSqlLite(getApplicationContext());
        SQLiteDatabase objBase = objConexion.getWritableDatabase();


    }
}