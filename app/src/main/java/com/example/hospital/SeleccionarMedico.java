package com.example.hospital;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import datos.ConexionSqlLite;

public class SeleccionarMedico extends AppCompatActivity
{
    Bundle RecibirData;
    private TextView NombreDr2, EspecialidadDR2, CodigoJVPM, EdadDR, TelefonoSelectDr, TotalPacientesTV;
    private int RolProf, IDDoc, Tpacientes;
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
        TotalPacientesTV = findViewById(R.id.TotalPacientesTV);

        GetPacientes();
        ArrayAdapter<String> Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, PacientesList);
        ListaPacientes.setAdapter(Adapter);
    }

    private void GetPacientes()
    {
        ConexionSqlLite ClaseCnx = new ConexionSqlLite(getApplicationContext());
        SQLiteDatabase ObBase = ClaseCnx.getWritableDatabase();
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
        IDDoc = RecibirData.getInt("IDdoctor");
        RolProf = RecibirData.getInt("Rol");

        if(RolProf == 2)
        {
            NombreDr2.setText("Srta. " +NombreIntenr);
            FotoProfMedico.setImageResource(R.drawable.recursos13);
            String ConsultarPacientes = "select paciente.nombres, paciente.apellidos from paciente join expedientes on expedientes.idPaciente = paciente.idPaciente where expedientes.idEnfermera = '"+IDDoc+"'";
            Cursor Pacientes = ObBase.rawQuery(ConsultarPacientes, null);

            while(Pacientes.moveToNext())
            {
                PacientesList.add(Pacientes.getString(0)  +" " +Pacientes.getString(1));
            }

            String ConsultarTotalPacientes = "select count(*) from paciente join expedientes on expedientes.idPaciente = paciente.idPaciente where expedientes.idEnfermera = '"+IDDoc+"'";
            Cursor TotalPacientes = ObBase.rawQuery(ConsultarTotalPacientes, null);

            if(TotalPacientes.moveToNext())
            {
                Tpacientes = TotalPacientes.getInt(0);
            }
            TotalPacientesTV.setText("Pacientes asignados: " +Tpacientes);
        }
        else if(RolProf == 1)
        {
            String ConsultarPacientes = "select paciente.nombres, paciente.apellidos from paciente join expedientes on expedientes.idPaciente = paciente.idPaciente where expedientes.idDoctor = '"+IDDoc+"'";
            Cursor Pacientes = ObBase.rawQuery(ConsultarPacientes, null);

            while(Pacientes.moveToNext())
            {
                PacientesList.add(Pacientes.getString(0)  +" " +Pacientes.getString(1));
            }

            String ConsultarTotalPacientes = "select count(*) from paciente join expedientes on expedientes.idPaciente = paciente.idPaciente where expedientes.idDoctor = '"+IDDoc+"'";
            Cursor TotalPacientes = ObBase.rawQuery(ConsultarTotalPacientes, null);

            if(TotalPacientes.moveToNext())
            {
                Tpacientes = TotalPacientes.getInt(0);
            }
            TotalPacientesTV.setText("Pacientes asignados: " +Tpacientes);
        }
    }
}