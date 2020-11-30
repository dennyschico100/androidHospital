package com.example.hospital;

import androidx.appcompat.app.AppCompatActivity;
import datos.ConexionSqlLite;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PacientesAdministracion_i extends AppCompatActivity {
    private ArrayList<Paciente> listaPacientes;
    private ListView lvPacientes;
    private EditText edtBuscar;
    ArrayList idPacientes=new ArrayList();
    Intent ig;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pacientes_administracion_i);
        ig=new Intent(this, EditarPaciente_I.class);
        llenarLista();
        Adaptador ad=new Adaptador(this);
        lvPacientes=(ListView) findViewById(R.id.lvListaPacientes);
        edtBuscar = (EditText)  findViewById(R.id.edtBuscar);
        lvPacientes.setAdapter(ad);
        lvPacientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(PacientesAdministracion_i.this, "ID: "+idPacientes.get(i), Toast.LENGTH_SHORT).show();
                ig.putExtra("id",Integer.parseInt(idPacientes.get(i).toString()));
                startActivity(ig);
            }
        });
    }
    class Adaptador extends ArrayAdapter<Paciente> {
        AppCompatActivity appCompatActivity;

        Adaptador(AppCompatActivity context){
            super(context, R.layout.listas_personalizadas, listaPacientes);
            appCompatActivity=context;
        }
        public View getView(int position, View convertView, ViewGroup parent){

            LayoutInflater inflater=appCompatActivity.getLayoutInflater();
            @SuppressLint("ViewHolder") View item=inflater.inflate(R.layout.listas_personalizadas , null);
            TextView textView1 =item.findViewById(R.id.txtNombreLista);
            TextView descripcion = item.findViewById(R.id.tvDescripcion);
            ImageView imageView1 =item.findViewById(R.id.img);
            if(listaPacientes.get(position).getUsaCama()==3)
            {
                textView1.setText(listaPacientes.get(position).getNombres() +" "+listaPacientes.get(position).getApellidos());
                descripcion.setText("PACIENTE DE ALTA");
                imageView1.setImageResource(R.drawable.recursos20);
            }else{
                textView1.setText(listaPacientes.get(position).getNombres() +" "+listaPacientes.get(position).getApellidos());
                descripcion.setText("DUI:"+listaPacientes.get(position).getDuiPaciente()+"\nEdad:"
                        +listaPacientes.get(position).getEdad()+"\nHabitacion:"+listaPacientes.get(position).getIdHabitacion());
                imageView1.setImageResource(R.drawable.recursos20);
            }


            return (item);
        }
    }
    public void llenarLista() {
        idPacientes.clear();
        listaPacientes = new ArrayList<Paciente>();
        String sql="SELECT * FROM paciente";
        Cursor datos = getConexion().rawQuery(sql, null);
        while(datos.moveToNext())
        {
            idPacientes.add(datos.getInt(0));
            addListItem(datos);
        }
    }
    public boolean validar()
    {
        if(edtBuscar.getText().toString().isEmpty()){
            edtBuscar.setError("Ingrese el valor a buscar");
            return false;
        }else{
            return true;
        }

    }
    public void addListItem(Cursor datos)
    {
        listaPacientes.add(new Paciente(datos.getString(1),
                datos.getString(2),
                datos.getString(3),
                datos.getInt(4),
                datos.getInt(5),
                datos.getString(6),
                datos.getInt(7),
                datos.getInt(8),
                datos.getString(9),
                datos.getString(10)));
    }
    public void buscar(View v){

        if(validar()){
            idPacientes.clear();
            String sql="select * from paciente where nombres like '%"+edtBuscar.getText().toString()+"%'";

            Cursor datos = getConexion().rawQuery(sql, null);
            int cont=0;
            if(datos.moveToNext())
            {
                idPacientes.add(datos.getInt(0));
                listaPacientes=new ArrayList<>();
                cont++;
                addListItem(datos);
                while (datos.moveToNext()){
                    idPacientes.add(datos.getInt(0));
                    addListItem(datos);
                    cont++;
                }
                Adaptador ad2=new Adaptador(this);
                lvPacientes.setAdapter(ad2);
            }else{
                Toast.makeText(this, "No se encontraron registros", Toast.LENGTH_SHORT).show();
            }
            if(cont!=0)
                Toast.makeText(this, "Se encontraron: "+cont+" Registros", Toast.LENGTH_SHORT).show();
        }

    }
    private SQLiteDatabase getConexion()
    {
        ConexionSqlLite objConexion = new ConexionSqlLite(getApplicationContext());
        return objConexion.getWritableDatabase();
    }
}