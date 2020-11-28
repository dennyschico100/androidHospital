package com.example.hospital;



class Usuarios {

    private int idUsuario;
    private String nombres;
    private String apellidos;
    private String password;
    private String preguntaSeguridad;
    private int edad;
    private int jvmp;
    private int rol;
    private String email;
    private int telefono;

    public  Usuarios(String s){

    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPreguntaSeguridad(String preguntaSeguridad) {
        this.preguntaSeguridad = preguntaSeguridad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setJvmp(int jvmp) {
        this.jvmp = jvmp;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public Usuarios( String nombres, String apellidos, String password, String preguntaSeguridad, int edad, int jvmp, int rol, String email, int telefono) {

        this.nombres = nombres;
        this.apellidos = apellidos;
        this.password = password;
        this.preguntaSeguridad = preguntaSeguridad;
        this.edad = edad;
        this.jvmp = jvmp;
        this.rol = rol;
        this.email = email;
        this.telefono = telefono;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getPassword() {
        return password;
    }

    public String getPreguntaSeguridad() {
        return preguntaSeguridad;
    }

    public int getEdad() {
        return edad;
    }

    public int getJvmp() {
        return jvmp;
    }

    public int getRol() {
        return rol;
    }

    public String getEmail() {
        return email;
    }

    public int getTelefono() {
        return telefono;
    }



}
