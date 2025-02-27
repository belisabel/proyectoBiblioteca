package com.egg.biblioteca.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egg.biblioteca.entities.Libro;
import com.egg.biblioteca.exceptions.MyException;
import com.egg.biblioteca.entities.Autor;
import com.egg.biblioteca.entities.Editorial;

import com.egg.biblioteca.repositories.LibroRepositorio;
import com.egg.biblioteca.repositories.AutorRepositorio;
import com.egg.biblioteca.repositories.EditorialRepositorio;


import jakarta.transaction.Transactional;

@Service
public class LibroServicio {

    @Autowired
    private EditorialRepositorio editorialRepositorio;

    @Autowired
    private LibroRepositorio libroRepositorio;

    @Autowired
    private AutorRepositorio autorRepositorio;

    @Transactional
    public void crearLibro(Long isbn, String titulo, Integer ejemplares, UUID idAutor, String idEditorial) throws MyException {

        validar(titulo,ejemplares, idAutor,idEditorial );
        Editorial editorial = editorialRepositorio.findById(idEditorial).get();// Instancio un objeto del tipo Editorial
        Libro libro = new Libro();// Seteo el atributo, con el valor recibido como parámetro
        Autor autor = autorRepositorio.findById(idAutor).get();
        libro.setIsbn(isbn);
        libro.setTitulo(titulo);
        libro.setEjemplares(ejemplares);
        libro.setAutor(autor);
        libro.setEditorial(editorial);
        libro.setAlta(new Date());
        libroRepositorio.save(libro);
    }

    @Transactional // (readOnly = true)
    public List<Libro> listarLibros() {

        List<Libro> libros = new ArrayList<>();

        libros = libroRepositorio.findAll();
        return libros;
    }

    @Transactional
    public void modificarLibro(String titulo, Long isbn)throws MyException {
        validar(titulo);
        Optional<Libro> respuesta = libroRepositorio.findById(isbn);
        if (respuesta.isPresent()) {
            Libro libro = respuesta.get();

            libro.setTitulo(titulo);
            libroRepositorio.save(libro);
        }
    }

    private void validar(String titulo,Integer ejemplares, UUID idAutor, String idEditorial ) throws MyException {
        if (titulo.isEmpty() || titulo == null) {
            throw new MyException("el nombre no puede ser nulo o estar vacío");
        }
        if (ejemplares== 0 || ejemplares == null) {
            throw new MyException("los ejemplares no pueden ser 0 o vacío");
        }
        if ((idAutor.toString()).isEmpty() || idAutor == null) {
            throw new MyException("el idAutor no puede ser nulo o estar vacío\"");
        }
        if (idEditorial.isEmpty() || idAutor == null) {
            throw new MyException("el idEditorial no puede ser nulo o estar vacío\"");
        }
    }

    private void validar(String titulo ) throws MyException {
        if (titulo.isEmpty() || titulo == null) {
            throw new MyException("el nombre no puede ser nulo o estar vacío");
        }

}
}
