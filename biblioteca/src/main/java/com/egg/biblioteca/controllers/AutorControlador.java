package com.egg.biblioteca.controllers;

import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.egg.biblioteca.entities.Autor;
import com.egg.biblioteca.exceptions.MyException;
import com.egg.biblioteca.services.AutorServicio;

import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;


@Controller
@RequestMapping("/autor") // localhost:8080/autor
public class AutorControlador {

    @Autowired
    private AutorServicio autorServicio;


    @GetMapping("/registrar") // localhost:8080/autor/registrar
    public String registrar() {
        return "autor_form.html";
    }


    @PostMapping("/registro") // localhost:8080/autor/registro
    public String registro(@RequestParam String nombre,ModelMap modelo) {
        try {
            autorServicio.crearAutor(nombre);    // llamo a mi servicio para persistir   
            modelo.put("exito", "El autor fue cargado exitosamente");     
        } catch (MyException ex) {
            
            modelo.put("error",ex.getMessage());
            Logger.getLogger(AutorControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "autor_form.html";
        }        
        return "index.html";
    }

    @GetMapping("/lista") // localhost:8080/autor/lista
    public String lista(ModelMap modelo) {

        List<Autor> autores1 = autorServicio.listarAutores();
    
        if (autores1 == null || autores1.isEmpty()) {
            System.out.println("La lista de autores está vacía.");
        } else {
            for (Autor autor : autores1) {
                System.out.println(autor.getNombre());
            }
        }
    
        return "autor_form.html";
     
       
    }
}