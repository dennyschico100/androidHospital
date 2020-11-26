package com.example.hospital;

class Habitaciones {

    private int idHabitacion;
    private int totalCamas;
    private int camasDisponbibles;
    private String tipo;
    private String equipamiento;



    public Habitaciones( int totalCamas, int camasDisponbibles, String tipo, String equipamiento) {

        this.totalCamas = totalCamas;
        this.camasDisponbibles = camasDisponbibles;
        this.tipo = tipo;
        this.equipamiento = equipamiento;
    }
    public Habitaciones(){

    }
    public int getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(int idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public int getTotalCamas() {
        return totalCamas;
    }

    public void setTotalCamas(int totalCamas) {
        this.totalCamas = totalCamas;
    }

    public int getCamasDisponbibles() {
        return camasDisponbibles;
    }

    public void setCamasDisponbibles(int camasDisponbibles) {
        this.camasDisponbibles = camasDisponbibles;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEquipamiento() {
        return equipamiento;
    }

    public void setEquipamiento(String equipamiento) {
        this.equipamiento = equipamiento;
    }




}
