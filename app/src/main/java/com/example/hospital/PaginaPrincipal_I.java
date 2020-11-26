package com.example.hospital;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.Calendar;

public class PaginaPrincipal_I extends AppCompatActivity {
    private TextView fecha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina_principal__i);
        fecha = (TextView)  findViewById(R.id.tvFecha);
        Calendar calendar = Calendar.getInstance();
        fecha.setText(calendar.getTime().toString());
    }
}