package com.example.hospital;

class Paciente {

    private int idPaciente;
    private String nombres;
    private String apellidos;
    private String duiPaciente;
    private int edad;
    private int telefono;
    private String aseguradora;
    private int idHabitacion;
    private int usaCama;
    private String duiResponsable;

    public String getTelefonoResponsable() {
        return telefonoResponsable;
    }

    public void setTelefonoResponsable(String telefonoResponsable) {
        this.telefonoResponsable = telefonoResponsable;
    }

    private String telefonoResponsable;

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDuiPaciente() {
        return duiPaciente;
    }

    public void setDuiPaciente(String duiPaciente) {
        this.duiPaciente = duiPaciente;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getAseguradora() {
        return aseguradora;
    }

    public void setAseguradora(String aseguradora) {
        this.aseguradora = aseguradora;
    }

    public int getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(int idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public int getUsaCama() {
        return usaCama;
    }

    public void setUsaCama(int usaCama) {
        this.usaCama = usaCama;
    }

    public String getDuiResponsable() {
        return duiResponsable;
    }

    public void setDuiResponsable(String duiResponsable) {
        this.duiResponsable = duiResponsable;
    }

    public Paciente() {

    }

    public Paciente( String nombres, String apellidos, String duiPaciente, int edad, int telefono, String aseguradora, int idHabitacion, int usaCama, String duiResponsable, String telefonoResponsable
    ) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.duiPaciente = duiPaciente;
        this.edad = edad;
        this.telefono = telefono;
        this.aseguradora = aseguradora;
        this.idHabitacion = idHabitacion;
        this.usaCama = usaCama;
        this.duiResponsable = duiResponsable;
        this.telefonoResponsable = telefonoResponsable;

    }
}
