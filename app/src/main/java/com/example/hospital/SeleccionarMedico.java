package com.example.hospital;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import datos.ConexionSqlLite;

public class SeleccionarMedico extends AppCompatActivity
{
    Bundle RecibirData;
    private TextView NombreDr2, EspecialidadDR2, CodigoJVPM, EdadDR, TelefonoSelectDr;
    private int RolProf, IDDoc;
    private ImageView FotoProfMedico;
    private ListView ListaPacientes;
    private ArrayList<String> PacientesList = new ArrayList<>();

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
        FotoProfMedico = findViewById(R.id.FotoProfMedico);
        ListaPacientes = findViewById(R.id.ListaPacientes);

        RecibirData = getIntent().getExtras();

        String NombreIntenr = RecibirData.getString("DoctorSelecionado");
        String Correo = RecibirData.getString("Correo");
        int JVPMDoc = RecibirData.getInt("JVPM");
        int Telefono = RecibirData.getInt("Telefono");
        int Edad = RecibirData.getInt("Edad");
        RolProf = RecibirData.getInt("Rol");


        GetPacientes();
        ArrayAdapter<String> Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, PacientesList);
        ListaPacientes.setAdapter(Adapter);

        NombreDr2.setText("Dr." +NombreIntenr);
        EspecialidadDR2.setText("Correo: " +Correo);
        CodigoJVPM.setText("JVPM: " +JVPMDoc);
        TelefonoSelectDr.setText("Telefono: " +Telefono);
        EdadDR.setText("Edad: " +Edad +" años");


        if(RolProf == 2)
        {
            NombreDr2.setText("Srta. " +NombreIntenr);
            FotoProfMedico.setImageResource(R.drawable.recursos13);
        }
    }

    private void GetPacientes()
    {
        ConexionSqlLite ClaseCnx = new ConexionSqlLite(getApplicationContext());
        SQLiteDatabase ObBase = ClaseCnx.getWritableDatabase();
        RecibirData = getIntent().getExtras();
        IDDoc = RecibirData.getInt("IDdoctor");

        Toast.makeText(getApplicationContext(), "ID: " +IDDoc, Toast.LENGTH_SHORT).show();
        String ConsultarPacientes = "select nombres, apellidos from paciente inner join expedientes where idDoctor = '"+IDDoc+"'";
        Cursor Pacientes = ObBase.rawQuery(ConsultarPacientes, null);

        while(Pacientes.moveToNext())
        {
            PacientesList.add(Pacientes.getString(0)  +" " +Pacientes.getString(1));
        }
    }
}