package com.egg.biblioteca.entities;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.web.multipart.MultipartFile;

import com.egg.biblioteca.enumerations.Rol;

import jakarta.persistence.*;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    String id;

    private String nombre;
    private String email;

    private String password;
    @Enumerated(EnumType.STRING)
    Rol rol;
    // Debemos indicar que es una enumeración con la anotación correspondiente e
    // indicar qué tipo de estrategia utilizará con el dato. En este caso:
    // EnumType.STRING

    @OneToOne(fetch = FetchType.LAZY)
    private Imagen imagen;

    public Imagen getImagen() {
        return imagen;
    }
    public void setImagen(Imagen imagen) {
        this.imagen = imagen;
    }
    public Usuario() {
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
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
    public Rol getRol() {
        return rol;
    }
    public void setRol(Rol rol) {
        this.rol = rol;
    }

}
