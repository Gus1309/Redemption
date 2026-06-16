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
        this(id, nombre, null, email, null, null);
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

    public abstract Rol getRol();
}
