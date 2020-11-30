package com.example.hospital;

public class csUsuariosID
{
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

    public csUsuariosID() { }

    public csUsuariosID(int idUsuario, String nombres, String apellidos, String password, String preguntaSeguridad, int edad, int jvmp, int rol, String email, int telefono)
    {
        this.idUsuario = idUsuario;
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

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPreguntaSeguridad() {
        return preguntaSeguridad;
    }

    public void setPreguntaSeguridad(String preguntaSeguridad) {
        this.preguntaSeguridad = preguntaSeguridad;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getJvmp() {
        return jvmp;
    }

    public void setJvmp(int jvmp) {
        this.jvmp = jvmp;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }
}
