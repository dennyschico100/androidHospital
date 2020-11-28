package com.example.hospital;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class SeleccionarMedico extends AppCompatActivity
{
    Bundle RecibirData;
    private TextView NombreDr2, Especialidad, JVPM;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccionar_medico);
        NombreDr2 = findViewById(R.id.NombreDr2);
        Especialidad = findViewById(R.id.Especialidad);
        JVPM = findViewById(R.id.JVPM);

        RecibirData = getIntent().getExtras();

        String NombreIntenr = RecibirData.getString("DoctorSelecionado");
        String JVPMDoc = RecibirData.getString("JVPM");

        NombreDr2.setText(NombreIntenr);
        Especialidad.setText(JVPMDoc);


    }
}