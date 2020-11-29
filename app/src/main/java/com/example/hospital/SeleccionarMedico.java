package com.example.hospital;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class SeleccionarMedico extends AppCompatActivity
{
    Bundle RecibirData;
    private TextView NombreDr2, EspecialidadDR2, JVPM2, EdadDR, TelefonoSelectDr;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccionar_medico);
        NombreDr2 = findViewById(R.id.NombreDr2);
        EspecialidadDR2 = findViewById(R.id.EspecialidadDR2);
        JVPM2 = findViewById(R.id.JVPM2);
        TelefonoSelectDr = findViewById(R.id.TelefonoSelectDr);
        EdadDR = findViewById(R.id.EdadDR);

        RecibirData = getIntent().getExtras();

        String NombreIntenr = RecibirData.getString("DoctorSelecionado");
        String Correo = RecibirData.getString("Correo");

        NombreDr2.setText(NombreIntenr);
        EspecialidadDR2.setText(Correo);
        JVPM2.setText(RecibirData.getString("JVPM"));
        TelefonoSelectDr.setText(RecibirData.getString("Telefono"));
        EdadDR.setText(RecibirData.getString("Edad"));
    }
}