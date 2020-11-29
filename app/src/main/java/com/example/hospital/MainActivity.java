package com.example.hospital;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import datos.ConexionSqlLite;

public class MainActivity extends AppCompatActivity {

//RAMA RICARDO
    private Button btnMostrar;
    private TextView tvIntegrantes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ConexionSqlLite objConexion = new ConexionSqlLite(getApplicationContext());
        SQLiteDatabase objBase = objConexion.getWritableDatabase();

        Usuarios usuarioDoctor;
        Usuarios usuarioEnfermera;
        Usuarios usuarioAdmin;

        Habitaciones habitacion;
        Paciente paciente;
        Expediente expediente;

        //ROLES 1=doctor,   2=enfermera,  3 =administrador
        usuarioDoctor = new Usuarios(
                "Ricardo",
                "Elias",
                "1234",
                "hoy",
                39,
                143,
                1,
                "jose@gmail.com",
                76576764

        );
        usuarioEnfermera = new Usuarios(
                "Sandra",
                "Saravia",
                "12345",
                "fresa",
                19,
                14,
                2,
                "lopez@gmail.com",
                70006764


        );
        usuarioAdmin = new Usuarios(
                "edwin",
                "martinez",
                "123456",
                "chocolate",
                29,
                0,
                3,
                "martinez@gmail.com",
                73419234


        );

        habitacion = new Habitaciones(
                4,
                4,
                "normal",
                "conTODO"

        );

        //usaCama 1 = SI , 2 = NO
        paciente = new Paciente(
                "Alcira",
                "Carmona",
                "05383983-2",
                20,
                7367819,
                "Seguros del pacicifico",
                1,
                1,
                "0585859-1",
                "25418272"

        );
        expediente= new Expediente(
                        1,
                        2,
                        1,
                        new Date(),
                        "resuman",
                        "indicaiones",
                        "acetaminofen alv"

        );

        //Boton de prueba , para mostrar los registros quemados
        btnMostrar = findViewById(R.id.btnMostrar);


        String guardarDoctor = "insert into " + objConexion.tblUsuarios + " (nombres, apellidos,password,preguntaSeguridad,edad,jvmp,rol,email,telefono) values " +
                "('" + usuarioDoctor.getNombres() + "','" + usuarioDoctor.getApellidos() + "','" + usuarioDoctor.getPassword() + "' ,'" + usuarioDoctor
                .getPreguntaSeguridad() + "','" + usuarioDoctor.getEdad() + "','" +
                usuarioDoctor.getJvmp() + "','" + usuarioDoctor.getRol() + "','" + usuarioDoctor.getEmail() + "','" + usuarioDoctor.getTelefono() + "'   )";

        String guardarEnfermera = "insert into " + objConexion.tblUsuarios + " (nombres, apellidos,password,preguntaSeguridad,edad,jvmp,rol,email,telefono) values " +
                "('" + usuarioEnfermera.getNombres() + "','" + usuarioEnfermera.getApellidos() + "','" + usuarioEnfermera.getPassword() + "' ,'" + usuarioEnfermera
                .getPreguntaSeguridad() + "','" + usuarioEnfermera.getEdad() + "','" +
                usuarioEnfermera.getJvmp() + "','" + usuarioEnfermera.getRol() + "','" + usuarioEnfermera.getEmail() + "','" + usuarioEnfermera.getTelefono() + "'   )";

        String guardarAdmin = "insert into " + objConexion.tblUsuarios + " (nombres, apellidos,password,preguntaSeguridad,edad,jvmp,rol,email,telefono) values " +
                "('" + usuarioAdmin.getNombres() + "','" + usuarioAdmin.getApellidos() + "','" + usuarioAdmin.getPassword() + "' ,'" + usuarioAdmin
                .getPreguntaSeguridad() + "','" + usuarioAdmin.getEdad() + "','" +
                usuarioAdmin.getJvmp() + "','" + usuarioAdmin.getRol() + "','" + usuarioAdmin.getEmail() + "','" + usuarioAdmin.getTelefono() + "'   )";

        String guardarHabitacion = "insert into " + objConexion.tblHabitacion + " (totalCamas,camasDisponibles,tipo,equipamiento ) values " +
                "('" + habitacion.getTotalCamas() + "','" + habitacion.getCamasDisponbibles() + "' ,'" + habitacion.getTipo() + "','" + habitacion.getEquipamiento() + "')";

        String guardarPaciente = "insert into " + objConexion.tblPaciente + " (nombres,apellidos,duiPaciente,edad,telefono,aseguradora,idHabitacion,usaCama,duiResponsable,telefonoResponsable ) values " +
                "('" + paciente.getNombres() + "','" + paciente.getApellidos() + "','" + paciente.getDuiPaciente() + "' ,'" + paciente.getEdad() + "','" + paciente
                .getTelefono() + "','"+paciente.getAseguradora()+"','"+paciente.getIdHabitacion()+"','"+paciente.getUsaCama()+"','"+paciente.getDuiResponsable()+"','"+paciente.getTelefonoResponsable()+"')";

        String guardarExpediente = "insert into " + objConexion.tblExpediente + " (idPaciente,idEnfermera,idDoctor,fechaAlta,resumenClinico,indicaciones,medicamentos ) values " +
                "('" + expediente.getIdPaciente() + "','" + expediente.getIdEnfermera() + "' ,'" + expediente.getIdDoctor() + "','" + expediente.
                getFechaAlta()+ "','"+expediente.getResumenClinico()+"','"+expediente.getIndicaciones()+"','"+expediente.getMedicamentos()+"');";


        Log.i("Query : ", guardarDoctor);
        Log.i("Query2 : ", guardarEnfermera);
        Log.i("Query3: ", guardarAdmin);

        objBase.execSQL(guardarDoctor);
        objBase.execSQL(guardarEnfermera);
        objBase.execSQL(guardarAdmin);

        objBase.execSQL(guardarHabitacion);
        objBase.execSQL(guardarPaciente);
        objBase.execSQL(guardarExpediente);






        Log.i("Mensaje Insert :", "USUARIOS INSERTADOS EN LA TABLA ");

        //mostrarRegistroAlumno();
        btnMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Open = new Intent(getApplicationContext(), IngresarPaciente.class);
                Intent Open2 = new Intent(getApplicationContext(), PersonalMedico.class);
                startActivity(Open2);
            }
        });

    }

    public void mostrarRegistrosUsuarios() {
        List<Usuarios> lista = new ArrayList<Usuarios>();
        //ArrayAdapter adapter;

        Usuarios users;

        ConexionSqlLite objConexion = new ConexionSqlLite(getApplicationContext());
        SQLiteDatabase objBase = objConexion.getWritableDatabase();
        String consulta = "SELECT *FROM usuarios";
        Cursor datos = objBase.rawQuery(consulta, null);

        /*while (datos.moveToNext()) {

            users = new Usuarios();


            users.setIdUsuario(datos.getInt(datos.getColumnIndex("idUsuario")));
            users.setNombres(datos.getString(datos.getColumnIndex("nombres")));
            users.setApellidos(datos.getString(datos.getColumnIndex("apellidos")));
            users.setPassword(datos.getString(datos.getColumnIndex("password")));
            users.setPreguntaSeguridad(datos.getString(datos.getColumnIndex("preguntaSeguridad")));
            users.setEdad(datos.getInt(datos.getColumnIndex("edad")));
            users.setJvmp(datos.getInt(datos.getColumnIndex("jvmp")));
            users.setEmail(datos.getString(datos.getColumnIndex("email")));
            users.setTelefono(datos.getInt(datos.getColumnIndex("telefono")));
            lista.add(users);

        }
        for (Usuarios u : lista) {


            Log.i("Datos", u.getIdUsuario() + " and " + u.getPassword());

        }

        String cadena = "";*/




    }

}