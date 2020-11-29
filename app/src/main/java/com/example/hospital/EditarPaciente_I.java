package com.example.hospital;

import androidx.appcompat.app.AppCompatActivity;
import datos.ConexionSqlLite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditarPaciente_I extends AppCompatActivity {
    private TextView tvNombre;

    private EditText nombre, apellidos, dui, edad, telefono,aseguradora;
    private CheckBox cama;
    private EditText duiResponsable,telefonoResponsable;
    int id;
    Paciente ps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_paciente__i);
        tvNombre=(TextView) findViewById(R.id.tvNombre);

        nombre=(EditText) findViewById(R.id.edtNombres);
        apellidos=(EditText) findViewById(R.id.edtApellidos);
        dui=(EditText) findViewById(R.id.edtDUI);
        edad=(EditText) findViewById(R.id.edtEdad);
        telefono=(EditText) findViewById(R.id.edtTelefono);
        aseguradora=(EditText) findViewById(R.id.edtAseguradora);

        cama=(CheckBox) findViewById(R.id.cbCama);

        duiResponsable=(EditText) findViewById(R.id.edtDUIResponsable);
        telefonoResponsable=(EditText) findViewById(R.id.edtTelefonoResponsable);
        Bundle parametros = this.getIntent().getExtras();
        id = parametros.getInt("id");

        ps=pacienteEditar();
        ps.setIdPaciente(id);
        if(ps!=null){
            nombre.setText(ps.getNombres());
            apellidos.setText(ps.getApellidos());
            dui.setText(ps.getDuiPaciente()+"");
            edad.setText(ps.getEdad()+"");
            telefono.setText(ps.getTelefono()+"");
            aseguradora.setText(ps.getAseguradora());
            cama.setChecked(ps.getUsaCama()==1? true: false);
            duiResponsable.setText(ps.getDuiResponsable()+"");
            telefonoResponsable.setText(ps.getTelefonoResponsable()+"");
            tvNombre.setText("Paciente ID: "+id);
        }


    }
    public void actualizar(View v)
    {
        ps.setNombres(nombre.getText().toString());
        ps.setApellidos(apellidos.getText().toString());
        ps.setDuiPaciente(dui.getText().toString());
        ps.setEdad(Integer.parseInt(edad.getText().toString()));
        ps.setTelefono(Integer.parseInt(telefono.getText().toString()));
        ps.setAseguradora(aseguradora.getText().toString());
        ps.setUsaCama(cama.isChecked()?1 : 2);
        ps.setDuiResponsable(duiResponsable.getText().toString());
        ps.setTelefonoResponsable(telefonoResponsable.getText().toString());

        String sql="UPDATE paciente SET " +
                   "nombres='"+ps.getNombres()+"'," +
                    "apellidos='"+ps.getApellidos()+"'," +
                    "duiPaciente='"+ps.getDuiPaciente()+"'," +
                    "edad="+ps.getEdad()+"," +
                    "telefono="+ps.getTelefono()+"," +
                    "aseguradora='"+ps.getAseguradora()+"'," +
                    "idHabitacion="+ps.getIdHabitacion()+"," +
                    "usaCama="+ps.getUsaCama()+"," +
                    "duiResponsable='"+ps.getDuiResponsable()+"'," +
                    "telefonoResponsable='"+ps.getTelefonoResponsable()+"'" +
                    "WHERE idPaciente="+ps.getIdPaciente()+"";
        try {
            getConexion().execSQL(sql);
            Toast.makeText(this, "Registro actualizado correctamente", Toast.LENGTH_SHORT).show();
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