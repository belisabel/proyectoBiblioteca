package com.egg.biblioteca.controllers;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.egg.biblioteca.exceptions.MyException;
import com.egg.biblioteca.services.AutorServicio;


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
    public String registro(@RequestParam String nombre) {
        try {
            autorServicio.crearAutor(nombre);    // llamo a mi servicio para persistir        
        } catch (MyException ex) {          
            Logger.getLogger(AutorControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "autor_form.html";
        }        
        return "index.html";
    }
}