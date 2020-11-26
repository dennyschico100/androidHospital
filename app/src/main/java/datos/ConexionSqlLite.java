package datos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Button;
import android.widget.EditText;


import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ConexionSqlLite extends SQLiteOpenHelper {


    public String tblUsuarios = "usuarios";
    public String tblPaciente = "paciente";
    public String tblHabitacion = "habitaciones";
    public String tblExpediente = "expedientes";


    public ConexionSqlLite(@Nullable Context context) {

        super(context, DatosConexion.NOMBREDB, null, DatosConexion.VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        String CREATE_TABLE_USUARIOS = "CREATE TABLE " + tblUsuarios + " (idUsuario   INTEGER PRIMARY KEY AUTOINCREMENT ,  " +
                "nombres TEXT, apellidos TEXT, password TEXT,preguntaSeguridad TEXT,edad INTEGER,jvmp INTEGER NULL,rol INTEGER,email TEXT,telefono INTEGER); ";


        String CREATE_TABLE_EXPEDIENTE = "CREATE TABLE " + tblExpediente + " (idExpediente  INTEGER PRIMARY KEY AUTOINCREMENT," +
                "idPaciente  INTEGER,idEnfermera INTEGER NULL ,idDoctor INTEGER NULL ,fechaAlta DATE,resumenClinico TEXT,indicaciones TEXT,medicamentos TEXT," +

                "Signo_TA TEXT,Signo_FCC TEXT,FOREIGN KEY(idEnfermera) REFERENCES " + tblUsuarios + "(idUsuario)," +
                "FOREIGN KEY(idDoctor) REFERENCES " + tblUsuarios + "(idUsuario) ) ; ";


        String CREATE_TABLE_PACIENTE = "CREATE TABLE " + tblPaciente + " (idPaciente INTEGER PRIMARY KEY AUTOINCREMENT , nombres TEXT,apellidos TEXT, " +
                "duiPaciente TEXT,edad INTEGER,telefono INTEGER,aseguradora TEXT,idHabitacion INTEGER,usaCama INTEGER, duiResponsable TEXT,telefonoResponsable INTEGER ); ";

        String CREATE_TABLE_HABITACIONES = "CREATE TABLE " + tblHabitacion + "(idHabitacion INTEGER PRIMARY KEY AUTOINCREMENT,totalCamas INTEGER, camasDisponibles INTEGER,tipo TEXT,equipamiento TEXT); ";

        db.execSQL(CREATE_TABLE_USUARIOS);

        db.execSQL(CREATE_TABLE_PACIENTE);
        db.execSQL(CREATE_TABLE_HABITACIONES);
        db.execSQL(CREATE_TABLE_EXPEDIENTE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

