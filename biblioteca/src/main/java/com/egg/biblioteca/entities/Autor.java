package com.egg.biblioteca.entities;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;

@Entity
public class Autor {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String nombre;

    // relación un autor a muchos libros. Para eliminar todos los libros asociados al autor especificado.
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Libro> libros;

    public Autor() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
