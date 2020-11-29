package com.example.hospital;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

public class SeleccionarMedico extends AppCompatActivity
{
    Bundle RecibirData;
    private TextView NombreDr2, EspecialidadDR2, CodigoJVPM, EdadDR, TelefonoSelectDr;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccionar_medico);
        NombreDr2 = findViewById(R.id.NombreDr2);
        EspecialidadDR2 = findViewById(R.id.EspecialidadDR2);
        CodigoJVPM = findViewById(R.id.CodigoJVPM);
        TelefonoSelectDr = findViewById(R.id.TelefonoSelectDr);
        EdadDR = findViewById(R.id.EdadDR);

        RecibirData = getIntent().getExtras();

        String NombreIntenr = RecibirData.getString("DoctorSelecionado");
        String Correo = RecibirData.getString("Correo");
        int JVPMDoc = RecibirData.getInt("JVPM");
        int Telefono = RecibirData.getInt("Telefono");
        int Edad = RecibirData.getInt("Edad");

        NombreDr2.setText("Dr." +NombreIntenr);
        EspecialidadDR2.setText("Correo: " +Correo);
        CodigoJVPM.setText("JVPM: " +JVPMDoc);
        TelefonoSelectDr.setText("Telefono: " +Telefono);
        EdadDR.setText("Edad: " +Edad +" años");
    }
}