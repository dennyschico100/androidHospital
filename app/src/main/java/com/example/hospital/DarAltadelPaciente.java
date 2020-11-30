package com.example.hospital;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import datos.ConexionSqlLite;

public class DarAltadelPaciente extends AppCompatActivity {
    private TextView tvNombre, txtresumen, txtindicaciones;
    private EditText nombre, apellidos, dui, edad, telefono,aseguradora,Fecha,Hora,resumen, indicaciones;
    int id;
    Paciente ps;
    Expediente ex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dar_altadel_paciente);
        tvNombre=(TextView) findViewById(R.id.tvNombre);
        txtresumen= (TextView) findViewById(R.id.txtresumen);
        txtindicaciones=(TextView)findViewById(R.id.txtindicaciones);

        nombre=(EditText) findViewById(R.id.edtNombres);
        apellidos=(EditText) findViewById(R.id.edtApellidos);
        dui=(EditText) findViewById(R.id.edtDUI);
        edad=(EditText) findViewById(R.id.edtEdad);
        telefono=(EditText) findViewById(R.id.edtTelefono);
        aseguradora=(EditText) findViewById(R.id.edtAseguradora);
        Fecha=(EditText) findViewById(R.id.edtfecha);
        Hora=(EditText) findViewById(R.id.edthora);
        resumen=(EditText) findViewById(R.id.edtresumen);
        indicaciones=(EditText) findViewById(R.id.edtindicaciones);

        Bundle parametros = this.getIntent().getExtras();
        //id = parametros.getInt("id");
        id = 5;

        ps=pacienteEditar();
        ps.setIdPaciente(id);
        if(ps!=null){
            nombre.setText(ps.getNombres());
            apellidos.setText(ps.getApellidos());
            dui.setText(ps.getDuiPaciente()+"");
            edad.setText(ps.getEdad()+"");
            telefono.setText(ps.getTelefono()+"");
            aseguradora.setText(ps.getAseguradora());
            tvNombre.setText("Paciente ID: "+id);
        }
    }

    public void darAlta(View v)
    {
        String sql="UPDATE paciente SET " +
                "usaCama=3 WHERE idPaciente="+ps.getIdPaciente()+"";
        try {
            getConexion().execSQL(sql);
            Toast.makeText(this, "Paciente dado de alta", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(this, "Algo fallo en la transaccion", Toast.LENGTH_SHORT).show();
        }
    }
    private SQLiteDatabase getConexion()
    {
        ConexionSqlLite objConexion = new ConexionSqlLite(getApplicationContext());
        return objConexion.getWritableDatabase();
    }
    public Paciente pacienteEditar()
    {
        String sql="SELECT * FROM paciente WHERE idPaciente="+id+"";
        Cursor datos = getConexion().rawQuery(sql, null);
        if(datos.moveToNext())
        {
            return new Paciente(datos.getString(1),
                    datos.getString(2),
                    datos.getString(3),
                    datos.getInt(4),
                    datos.getInt(5),
                    datos.getString(6),
                    datos.getInt(7),
                    datos.getInt(8),
                    datos.getString(9),
                    datos.getString(10));
        }else{
            return null;
        }
    }

}