package com.example.hospital;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import datos.ConexionSqlLite;

public class IngresarPaciente extends AppCompatActivity
{
    private Spinner Aseguradora, EnfermerasSpin, MedicosSpin;
    private Button IngrPaciente;
    private TextView Habitaciones;
    private int HabDisponibles, IDPaci;
    private String SelecAseguradora, SelectEnferm, SelectMed, IDSepEn, IDSepDr;
    private EditText NombrePaciente, ApellidoPacient, DUI, Edad, Telefono, Responsable, DUIRes, TelefonoRes, MotivoConsulta;
    String[] NombresAseg = {"ASESUISA" , "SISA" , "ISBM" , "MAPFRE" , "ACSA" , "Seguros del pacifico" , "Seguros azul" , "ASA"};
    ArrayList<String> ListaDR = new ArrayList<>();
    ArrayList<String> ListaEF = new ArrayList<>();
    ArrayList<String> ListaIDoc = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar_paciente);

        NombrePaciente = findViewById(R.id.NombrePacient);
        ApellidoPacient = findViewById(R.id.ApellidoPacient);
        DUI = findViewById(R.id.DUI);
        Edad = findViewById(R.id.Edad);
        Telefono = findViewById(R.id.Telefono);
        Responsable = findViewById(R.id.Responsable);
        DUIRes = findViewById(R.id.DUIRes);
        TelefonoRes = findViewById(R.id.TelefonoRes);
        IngrPaciente = findViewById(R.id.IngrPaciente);
        Aseguradora = findViewById(R.id.Aseguradora);
        EnfermerasSpin = findViewById(R.id.EnfermerasSpin);
        MedicosSpin = findViewById(R.id.MedicosSpin);
        Habitaciones = findViewById(R.id.Habitaciones);
        MotivoConsulta = findViewById(R.id.MotivoConsulta);

        ConexionSqlLite objConexion = new ConexionSqlLite(getApplicationContext());
        final SQLiteDatabase objBase = objConexion.getWritableDatabase();
        Consultas();
        CargarSpiners();

        Aseguradora.setAdapter(new ArrayAdapter<>(IngresarPaciente.this , android.R.layout.simple_expandable_list_item_1 , NombresAseg));
        MedicosSpin.setAdapter(new ArrayAdapter<>(IngresarPaciente.this, android.R.layout.simple_expandable_list_item_1, ListaDR));
        EnfermerasSpin.setAdapter(new ArrayAdapter<>(IngresarPaciente.this, android.R.layout.simple_expandable_list_item_1, ListaEF));

        IngrPaciente.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(Validar())
                {
                    String[] IDEnfermera = SelectEnferm.split(" ");
                    IDSepEn = IDEnfermera[0];
                    String[] IDDoctor = SelectMed.split(" ");
                    IDSepDr = IDDoctor[0];
                    int IDr = Integer.parseInt(IDSepDr);
                    int IEn = Integer.parseInt(IDSepEn);

                    String IngresarPaciente = "insert into paciente (nombres,apellidos,duiPaciente,edad,telefono,aseguradora,idHabitacion,usaCama,duiResponsable,telefonoResponsable ) values " +
                            "('" +NombrePaciente.getText().toString() + "','" + ApellidoPacient.getText().toString() + "','" + DUI.getText().toString() + "' ,'" + Edad.getText().toString() + "','" + Telefono.getText().toString() + "','"+SelecAseguradora+"', 1, 1,'"+Responsable.getText().toString()+"','"+TelefonoRes.getText().toString()+"')";
                    objBase.execSQL(IngresarPaciente);

                    String ConsultarIdPaci = "select idPaciente from paciente where duiPaciente = '"+DUI.getText().toString()+"'" ;
                    Cursor IDPaciente = objBase.rawQuery(ConsultarIdPaci, null);

                    if(IDPaciente.moveToNext())
                    {
                        IDPaci = IDPaciente.getInt(0);
                    }

                    String GuardarExpediente = "insert into expedientes (idPaciente,idEnfermera,idDoctor,resumenClinico) values " +
                            "('" +IDPaci + "','" +IEn + "' ,'" +IDr+ "', '"+MotivoConsulta.getText().toString()+"')";
                    objBase.execSQL(GuardarExpediente);

                    int TotalHab = HabDisponibles - 1;

                    String UpdateHab = "update habitaciones set  camasDisponibles = '"+TotalHab+"'";
                    objBase.execSQL(UpdateHab);

                    Toast.makeText(getApplicationContext(), "Paciente ingresado correctamente!" +IDSepDr  , Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Por favor, complete los campos" , Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public  void CargarSpiners()
    {
        Aseguradora.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                SelecAseguradora = Aseguradora.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });

        MedicosSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                SelectMed = MedicosSpin.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });

        EnfermerasSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                SelectEnferm = EnfermerasSpin.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });
    }

    public boolean Validar()
    {
        if(!NombrePaciente.getText().toString().isEmpty() && !ApellidoPacient.getText().toString().isEmpty() && !DUI.getText().toString().isEmpty() && !Telefono.getText().toString().isEmpty() && !Edad.getText().toString().isEmpty() && !Responsable.getText().toString().isEmpty() && !DUIRes.getText().toString().isEmpty() && !TelefonoRes.getText().toString().isEmpty() && !MotivoConsulta.getText().toString().isEmpty())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void Consultas()
    {
        ConexionSqlLite objConexion = new ConexionSqlLite(getApplicationContext());
        SQLiteDatabase objBase2 = objConexion.getWritableDatabase();

        String ConsultarHab = "select camasDisponibles from Habitaciones";
        Cursor Datos = objBase2.rawQuery(ConsultarHab, null);

        if(Datos.moveToNext())
        {
            HabDisponibles = Datos.getInt(0);
        }

        Habitaciones.setText("Habitaciones disponibles: " +HabDisponibles);

        String ConsultarDocs = "select * from usuarios where rol = 1";
        Cursor Datos3 = objBase2.rawQuery(ConsultarDocs , null);

        while(Datos3.moveToNext())
        {
            ListaDR.add(Datos3.getString(0) +" -Dr. " +Datos3.getString(1)  +Datos3.getString(2));
        }

        String ConsultarEnf = "Select * from usuarios where rol = 2";
        Cursor Datos2 = objBase2.rawQuery(ConsultarEnf, null);

        while(Datos2.moveToNext())
        {
            ListaEF.add(Datos2.getString(0) +" -Srta. " +Datos2.getString(1) +" " +Datos2.getString(2));
        }
    }
}