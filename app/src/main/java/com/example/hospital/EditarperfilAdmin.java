package com.example.hospital;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import datos.ConexionSqlLite;

public class EditarperfilAdmin extends AppCompatActivity {

    private TextView txtperfiladmin, tvNombre;
    private EditText edtNombres,edtApellidos,edtEmpleado,edtCargo,edtTelefono;
    private Button btnguardar;
    int id;
    Usuarios us;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editarperfil_admin);

        txtperfiladmin = findViewById(R.id.txtperfiladmin);

        edtNombres = findViewById(R.id.edtNombres);
        edtApellidos = findViewById(R.id.edtApellidos);
        edtEmpleado = findViewById(R.id.edtEmpleado);
        edtCargo = findViewById(R.id.edtCargo);
        edtTelefono = findViewById(R.id.edtTelefono);
        btnguardar = findViewById(R.id.btnguardar);
        tvNombre.setText("Usuarios ID: "+id);


        Bundle parametros = this.getIntent().getExtras();
        //id = parametros.getInt("id");
        id = 5;
        us = new Usuarios();

        us=perfilEditar();
        us.setIdUsuario(id);

        if(us!=null){
            edtNombres.setText(us.getNombres());
            edtApellidos.setText(us.getApellidos());
            edtEmpleado.setText(us.getJvmp()+"");
            edtCargo.setText(us.getRol()+"");
            edtTelefono.setText(us.getTelefono()+"");
            tvNombre.setText("Usuarios ID: "+id);
        }
    }
    public void guardarcambios(View view)
    {
        us.setNombres(edtNombres.getText().toString());
        us.setApellidos(edtApellidos.getText().toString());
        us.setRol(Integer.parseInt(edtCargo.getText().toString()));
        us.setJvmp(Integer.parseInt(edtEmpleado.getText().toString()));
        us.setTelefono(Integer.parseInt(edtTelefono.getText().toString()));

        String sql="UPDATE Usuarios SET " +
                "nombres='"+us.getNombres()+"'," +
                "apellidos='"+us.getApellidos()+"'," +
                "password='"+us.getPassword()+"'," +
                "preguntaSeguridad="+us.getPreguntaSeguridad()+"," +
                "edad="+us.getEdad()+"," +
                "jvmp='"+us.getJvmp()+"'," +
                "rol="+us.getRol()+"," +
                "email="+us.getEmail()+"," +
                "telefono='"+us.getTelefono()+"'," +
                "WHERE idUsuarios="+us.getIdUsuario()+"";
        try {
            getConexion().execSQL(sql);
            Toast.makeText(this, "Registro guardado correctamente", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(this, "Algo fallo en la transaccion", Toast.LENGTH_SHORT).show();
        }
    }

    public Usuarios perfilEditar()
    {
        String sql="SELECT * FROM Usuarios WHERE idUsuarios="+id+"";
        Cursor datos = getConexion().rawQuery(sql, null);
        if(datos.moveToNext())
        {
            return new Usuarios(datos.getString(1),
                    datos.getString(2),
                    datos.getString(3),
                    datos.getString(4),
                    datos.getInt(5),
                    datos.getInt(6),
                    datos.getInt(7),
                    datos.getString(8),
                    datos.getInt(9));
        }else {
            return null;
        }
    }

    private SQLiteDatabase getConexion()
    {
        ConexionSqlLite objConexion = new ConexionSqlLite(getApplicationContext());
        return objConexion.getWritableDatabase();
    }

}