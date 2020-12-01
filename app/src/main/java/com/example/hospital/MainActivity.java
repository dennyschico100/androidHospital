package com.example.hospital;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
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
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import datos.ConexionSqlLite;

public class MainActivity extends AppCompatActivity {

    private Button btnMostrar, btnIniciar, btnRegistrar;
    private TextView tvIntegrantes, tvemailError, tvpasswordError;
    private EditText edtUsuario, edtPassword;

    int tipoRegistro = 0;
    /*private static final String COLUMN_USER_ID = "idUsuario";
    private static final String COLUMN_USER_NOMBRE = "nombres";
    private static final String COLUMN_USER_EMAIL = "email";
    private static final String COLUMN_USER_PASSWORD = "password";*/

    ConexionSqlLite objConexion;
    SQLiteDatabase objBase;
    Usuarios usuarioSession;
    Intent intent;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        guardarRegistrosDefault();


        edtUsuario = findViewById(R.id.ediUsuario);
        edtPassword = findViewById(R.id.edtPassword);
        btnIniciar = findViewById(R.id.btnIniciar);
        tvemailError = findViewById(R.id.emailError);
        tvpasswordError = findViewById(R.id.passwordError);
        tvemailError.setVisibility(View.INVISIBLE);
        tvpasswordError.setVisibility(View.INVISIBLE);
        btnRegistrar = findViewById(R.id.btnRegistrar);
        intent = new Intent(getApplicationContext(), Registro.class);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                builder = new AlertDialog.Builder(MainActivity.this);

                builder.setMessage("Seleccion");
                //Setting message manually and performing action on button click
                builder.setMessage("Selecciona como te quieres registrar")
                        .setCancelable(false)
                        .setPositiveButton("Administrativo", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                tipoRegistro = 1;
                                intent.putExtra("tipoRegistro", tipoRegistro);

                                finish();
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("Doctor/Enfermera", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                tipoRegistro = 2;
                                //  Action for 'NO' Button
                                intent.putExtra("tipoRegistro", tipoRegistro);


                                finish();
                                startActivity(intent);


                            }
                        });
                //Creating dialog box
                AlertDialog alert = builder.create();
                //Setting the title manually
                alert.setTitle("Registro");
                alert.show();


            }
        });


        //Boton de prueba , para mostrar los registros quemados
        btnMostrar = findViewById(R.id.btnMostrar);

        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarUsuario();

            }
        });
        //mostrarRegistroAlumno();
        btnMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarRegistrosUsuarios();
            }
        });

    }

    public void validarUsuario() {
        objConexion = new ConexionSqlLite(getApplicationContext());
        objBase = objConexion.getWritableDatabase();

        String pass = "";
        String usuario = "";
        usuario = edtUsuario.getText().toString();
        pass = edtPassword.getText().toString();
        /*String[] columns = {
                COLUMN_USER_EMAIL,
                COLUMN_USER_PASSWORD
        };*/

        String TABLE_USER = "SELECT *FROM usuarios where email=? and password=? ";
        //String selection = COLUMN_USER_EMAIL + " = ?" + " AND " + COLUMN_USER_PASSWORD + " = ?";

        String[] selectionArgs = {usuario, pass};

        if (edtUsuario.getText().length() == 0 || edtUsuario.getText().length() <= 4) {
            tvemailError.setVisibility(View.VISIBLE);
        } else if (edtUsuario.getText().length() == 0 || edtUsuario.getText().length() <= 4) {
            tvpasswordError.setText("ingresa una contraseña con almenos 4 caracteres");
        } else {
            Cursor datos = objBase.rawQuery(TABLE_USER, //Table to query
                    selectionArgs             //The values for the WHERE clause
            );

            int cursorCount = datos.getCount();
            if (cursorCount > 0) {

                Log.i("RESULTADO", "EXISTE USUARIO");
                while (datos.moveToNext()) {
                    usuarioSession = new Usuarios();

                    usuarioSession.setIdUsuario(datos.getInt(datos.getColumnIndex("idUsuario")));
                    usuarioSession.setNombres(datos.getString(datos.getColumnIndex("nombres")));

                    usuarioSession.setApellidos(datos.getString(datos.getColumnIndex("apellidos")));
                    usuarioSession.setPassword(datos.getString(datos.getColumnIndex("password")));
                    usuarioSession.setPreguntaSeguridad(datos.getString(datos.getColumnIndex("preguntaSeguridad")));
                    usuarioSession.setEdad(datos.getInt(datos.getColumnIndex("edad")));
                    usuarioSession.setJvmp(datos.getInt(datos.getColumnIndex("jvmp")));
                    usuarioSession.setEmail(datos.getString(datos.getColumnIndex("email")));
                    usuarioSession.setTelefono(datos.getInt(datos.getColumnIndex("telefono")));
                    Log.i("usuario", "" + usuarioSession.toString());
                }

            } else {

                Log.i("RESULTADO NO EXISTE", "CREDENCIALES INCORRECTAS");
            }

        }


    }

    public void guardarRegistrosDefault() {
        objConexion = new ConexionSqlLite(getApplicationContext());
        objBase = objConexion.getWritableDatabase();

        Usuarios usuarioDoctor;
        Usuarios usuarioEnfermera;
        Usuarios usuarioAdmin;

        Habitaciones habitacion;
        Paciente paciente;
        Expediente expediente;

        //ROLES 1=doctor,   2=enfermera,  3 =administrador
        usuarioDoctor = new Usuarios(
                "jose",
                "perez",
                "1234",
                "hoy",
                39,
                143,
                1,
                "jose@gmail.com",
                76576764

        );
        usuarioEnfermera = new Usuarios(
                "maria",
                "lopez",
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
                "pacienteUno",
                "ApellidoPacinteUno",
                "05383983-2",
                20,
                7367819,
                "Seguros del pacicifico",
                1,
                1,
                "0585859-1",
                "25418272"

        );
        expediente = new Expediente(
                1,
                2,
                1,
                new Date(),
                "resuman",
                "indicaiones",
                "acetaminofen alv"

        );


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
                .getTelefono() + "','" + paciente.getAseguradora() + "','" + paciente.getIdHabitacion() + "','" + paciente.getUsaCama() + "','" + paciente.getDuiResponsable() + "','" + paciente.getTelefonoResponsable() + "')";

        String guardarExpediente = "insert into " + objConexion.tblExpediente + " (idPaciente,idEnfermera,idDoctor,fechaAlta,resumenClinico,indicaciones,medicamentos ) values " +
                "('" + expediente.getIdPaciente() + "','" + expediente.getIdEnfermera() + "' ,'" + expediente.getIdDoctor() + "','" + expediente.
                getFechaAlta() + "','" + expediente.getResumenClinico() + "','" + expediente.getIndicaciones() + "','" + expediente.getMedicamentos() + "');";

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
        mostrarRegistrosUsuarios();
    }


    public void mostrarRegistrosUsuarios() {
        List<Usuarios> lista = new ArrayList<Usuarios>();
        //ArrayAdapter adapter;

        Usuarios users;

        String consulta = "SELECT *FROM usuarios";
        Cursor datos = objBase.rawQuery(consulta, null);

        while (datos.moveToNext()) {

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


            Log.i("Datos", u.getNombres() + " and " + u.getPassword());

        }

        String cadena = "";


    }

}