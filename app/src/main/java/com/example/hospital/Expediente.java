package com.example.hospital;

import java.util.Date;


class Expediente {

    private int idExpediente;
    private int idPaciente;

    private int idEnfermera;
    private int idDoctor;
    private Date fechaAlta;
    private String resumenClinico;
    private String indicaciones;
    private String medicamentos;

    public Expediente(int idPaciente,  int idEnfermera, int idDoctor, Date fechaAlta, String resumenClinico, String indicaciones, String medicamentos) {
        this.idExpediente = idExpediente;
        this.idPaciente = idPaciente;
        this.idEnfermera = idEnfermera;
        this.idDoctor = idDoctor;
        this.fechaAlta = fechaAlta;
        this.resumenClinico = resumenClinico;
        this.indicaciones = indicaciones;
        this.medicamentos = medicamentos;
    }
    public Expediente(){

    }
    public int getIdExpediente() {
        return idExpediente;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public int getIdEnfermera() {
        return idEnfermera;
    }

    public int getIdDoctor() {
        return idDoctor;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public String getResumenClinico() {
        return resumenClinico;
    }

    public String getIndicaciones() {
        return indicaciones;
    }

    public String getMedicamentos() {
        return medicamentos;
    }



}
