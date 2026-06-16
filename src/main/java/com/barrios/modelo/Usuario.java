package com.barrios.modelo;

public abstract class Usuario {
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private String password;
    private String rolTexto;

    public Usuario() {
    }

    public Usuario(Long id, String nombre, String email) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
    }

    public Usuario(Long id, String nombre, String apellido, String email, String password, String rolTexto) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        this.rolTexto = rolTexto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRolTexto() {
        return rolTexto;
    }

    public void setRolTexto(String rolTexto) {
        this.rolTexto = rolTexto;
    }

    public abstract Rol getRol();
}
