package com.example.hospital;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class Registro extends AppCompatActivity {

    private ListView listaPreguntas;
    private Spinner spinnerEspecialidades, spinnerPregunta;

    Usuarios usuarios;
    private Button btnRegistrarse;
    private EditText edtNombres,edtApellidos,edtEmail,edtPassword,edtJvmp,edtSeguridad,edtEdad,edtTelefono;
    int rol = 0, tipoRegistro = 0;
    String preguntaSeguridad, especialidad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        Bundle getResults = getIntent().getExtras();
        tipoRegistro = getResults.getInt("tipoRegistro");
        btnRegistrarse=findViewById(R.id.btnRegistrar);
        edtNombres=findViewById(R.id.edtNombres);
        edtApellidos=findViewById(R.id.edtApellidos);
        edtPassword=findViewById(R.id.edtPassword);
        edtEmail=findViewById(R.id.edtEmail);
        edtSeguridad=findViewById(R.id.edtPregunta);
        edtEdad=findViewById(R.id.edtEdad);
        edtTelefono=findViewById(R.id.edtTelefono);


        edtJvmp = findViewById(R.id.edtJvmp);


        if (tipoRegistro == 1) {
            edtJvmp.setVisibility(View.INVISIBLE);
        }

        spinnerEspecialidades = findViewById(R.id.spinnerEspecialidades);
        spinnerPregunta = findViewById(R.id.spinnerPregunta);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.especialidades, android.R.layout.simple_spinner_item);

        ArrayAdapter<CharSequence> adapterPregunta = ArrayAdapter.createFromResource(this,
                R.array.preguntas, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterPregunta.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

       spinnerEspecialidades.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               //Toast.makeText(Registro.this, ""+spinnerPregunta.getSelectedItemId(), Toast.LENGTH_SHORT).show();

           }

           @Override
           public void onNothingSelected(AdapterView<?> adapterView) {

           }
       });

        spinnerPregunta.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(Registro.this, ""+spinnerPregunta.getSelectedItemId(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinnerPregunta.setAdapter(adapterPregunta);
        spinnerEspecialidades.setAdapter(adapter);

        btnRegistrarse.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              usuarios=new Usuarios();
              usuarios.setNombres(edtNombres.getText().toString());
              usuarios.setApellidos(edtApellidos.getText().toString());
              if(tipoRegistro!=1){
                  usuarios.setJvmp(Integer.parseInt(edtJvmp.getText().toString()));

              }
              usuarios.setRol( (int) spinnerEspecialidades.getSelectedItemId() );
              usuarios.setEmail(edtEmail.getText().toString());
              usuarios.setPassword(edtPassword.getText().toString());
              usuarios.setPreguntaSeguridad(edtSeguridad.getText().toString());
              usuarios.setEdad(Integer.parseInt(edtEdad.getText().toString()));
              usuarios.setTelefono(Integer.parseInt(edtTelefono.getText().toString()));
              Toast.makeText(getApplicationContext(),""+usuarios.toString(),Toast.LENGTH_SHORT).show();


          }
        }
        );


    }
}