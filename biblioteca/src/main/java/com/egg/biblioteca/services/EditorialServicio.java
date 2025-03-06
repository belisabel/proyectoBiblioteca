package com.egg.biblioteca.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egg.biblioteca.entities.Autor;
import com.egg.biblioteca.entities.Editorial;
import com.egg.biblioteca.exceptions.MyException;

import com.egg.biblioteca.repositories.EditorialRepositorio;

import jakarta.transaction.Transactional;

@Service
public class EditorialServicio {

    @Autowired
    private EditorialRepositorio editorialRepositorio;

    @Transactional
    public void crearEditorial(String nombre) throws MyException {

        validar(nombre);
        Editorial editorial = new Editorial();// Instancio un objeto del tipo Editorial
        editorial.setNombre(nombre);// Seteo el atributo, con el valor recibido como parámetro

        editorialRepositorio.save(editorial); // Persisto el dato en mi BBDD
    }

    public List<Editorial> listarEditoriales() {

        List<Editorial> editoriales = new ArrayList<>();

        editoriales = editorialRepositorio.findAll();
        return editoriales;
    }

    @Transactional
    public void modificarEditorial(String nombre, String id) throws MyException {
        validar(nombre);
        Optional<Editorial> respuesta = editorialRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Editorial editorial = respuesta.get();

            editorial.setNombre(nombre);
            editorialRepositorio.save(editorial);
        }
    }

    private void validar(String nombre) throws MyException {
        if (nombre.isEmpty() || nombre == null) {
            throw new MyException("el nombre no puede ser nulo o estar vacío");
        }
    }

        @Transactional //(readOnly = true)
    public Editorial getOne(String id) {
        return editorialRepositorio.getReferenceById(id);
    }


}
